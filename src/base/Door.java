package base;

import base.DoorState.DoorState;
import base.DoorState.UnlockedDoor;
import base.areas.Area;
import base.areas.Space;
import base.requests.RequestReader;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;

public class Door extends Observable {
  private final String id;
  private boolean closed; // physically
  private DoorState state;
  private final Space from;
  private final Space to;

  public Door(String id, String from, String to) {
    this.id = id;
    closed = true;
    state = new UnlockedDoor(this);


    Logger logger = LoggerFactory.getLogger("base.Door");
    DirectoryAreas directory = DirectoryAreas.getInstance();
    //Ensure the areas retrieved are instances of Partition, and set them to the class attributes.
    Area fromArea = directory.findAreaById(from);
    Area toArea = directory.findAreaById(to);
    if (!(fromArea instanceof Space)) {
      logger.warn("Warning: Area with id '" + from + "' is not a Partition.");
      throw new IllegalArgumentException("Area with id '" + from + "' is not a Partition.");
    }
    if (!(toArea instanceof Space)) {
      logger.warn("Warning: Area with id '" + to + "' is not a Partition.");
      throw new IllegalArgumentException("Area with id '" + from + "' is not a Partition.");
    }

    this.from = (Space) fromArea;
    this.to = (Space) toArea;

    this.from.addOutDoor(this);
    this.to.addInDoor(this);

  }

  public void setState(DoorState state) {
    this.state = state;
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    String action = request.getAction();
    if (action.equals("open") || action.equals("close") || request.isAuthorized()) {
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        closed = state.open();
        break;
      case Actions.CLOSE:
        closed = state.close();
        setChanged();
        notifyObservers(this);
        break;
      case Actions.LOCK:
        state = state.lock();
        break;

      case Actions.UNLOCK:
        state = state.unlock();

        break;
      case Actions.UNLOCK_SHORTLY:
        state = state.unlock_shortly();

        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return closed;
  }

  public String getId() {
    return id;
  }

  public Space getFromSpace() {
    return this.from;
  }

  public Space getToSpace() {
    return this.to;
  }

  public String getStateName() {
    return state.getName();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }
}
