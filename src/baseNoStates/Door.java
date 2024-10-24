package baseNoStates;

import baseNoStates.DoorState.UnlockedDoor;
import baseNoStates.areas.Partition;
import baseNoStates.areas.Space;
import baseNoStates.requests.RequestReader;
import baseNoStates.DoorState.DoorState;
import org.json.JSONObject;


public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorState state;
  private Partition from;
  private Partition to;

  public Door(String id, String from , String to ) {
    this.id = id;
    closed = true;
    state = new UnlockedDoor(this);
    this.from= DirectoryAreas.findPartitionById(from);
    this.to= DirectoryAreas.findPartitionById(to);
    this.from.addOutDoor(this);
    this.to.addInDoor(this);

  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
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
