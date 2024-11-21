package base;

import base.DoorState.UnlockedDoor;
import base.areas.Partition;
import base.requests.RequestReader;
import base.DoorState.DoorState;
import org.json.JSONObject;
import java.util.Observable;

public class Door extends Observable {
  private final String id;
  private boolean closed; // physically
  private DoorState state;
  private final Partition from;
  private final Partition to;

  public Door(String id, String from , String to ) {
    this.id = id;
    closed = true;
    state = new UnlockedDoor(this);
    DirectoryAreas directory = DirectoryAreas.getInstance();
    this.from= directory.findPartitionById(from);
    this.to= directory.findPartitionById(to);
    this.from.addOutDoor(this);
    this.to.addInDoor(this);

  }

  public void setState(DoorState state) {this.state = state;}
  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    String action = request.getAction();
    if (action.equals( "open") || action.equals("close") || request.isAuthorized()) {
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
  public Partition  getFromSpace() { return this.from; }
  public Partition  getToSpace() { return this.to; }

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
