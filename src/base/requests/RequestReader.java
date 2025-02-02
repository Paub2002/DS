package base.requests;

import base.DirectoryDoors;
import base.DirectoryUsers;
import base.Door;
import base.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;

/*
Read Requests and get subject to process them
 */
public class RequestReader implements Request {
  private final String credential; // who
  private final String action;     // what
  private final LocalDateTime now; // when
  private final String doorId;     // where
  private final ArrayList<String> reasons; // why not authorized
  private String userName;
  private boolean authorized;
  private String doorStateName;
  private boolean doorClosed;

  public RequestReader(String credential, String action, LocalDateTime now, String doorId) {
    this.credential = credential;
    this.action = action;
    this.doorId = doorId;
    reasons = new ArrayList<>();
    this.now = now;
  }

  public void setDoorStateName(String name) {
    doorStateName = name;
  }

  public String getAction() {
    return action;
  }

  public boolean isAuthorized() {
    return authorized;
  }

  public void addReason(String reason) {
    reasons.add(reason);
  }


  @Override
  public String toString() {
    if (userName == null) {
      userName = "unknown";
    }
    return "Request{"
        + "credential=" + credential
        + ", userName=" + userName
        + ", action=" + action
        + ", now=" + now
        + ", doorID=" + doorId
        + ", closed=" + doorClosed
        + ", authorized=" + authorized
        + ", reasons=" + reasons
        + "}";
  }

  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("authorized", authorized);
    json.put("action", action);
    json.put("doorId", doorId);
    json.put("closed", doorClosed);
    json.put("state", doorStateName);
    json.put("reasons", new JSONArray(reasons));
    return json;
  }

  // see if the request is authorized and put this into the request, then send it to the door.
  // if authorized, perform the action.
  public void process() {
    DirectoryUsers directoryUsers = DirectoryUsers.getInstance();
    DirectoryDoors directoryDoors = DirectoryDoors.getInstance();
    User user = directoryUsers.findUserByCredential(credential);
    Door door = directoryDoors.findDoorById(doorId);
    assert door != null : "door " + doorId + " not found";
    authorize(user, door);
    // this sets the boolean authorize attribute of the request
    door.processRequest(this);
    // even if not authorized we process the request, so that if desired we could log all
    // the requests made to the server as part of processing the request
    doorClosed = door.isClosed();
  }

  // the result is put into the request object plus, if not authorized, why not,
  // only for testing
  private void authorize(User user, Door door) {
    if (action.equals("open") || action.equals("close")) {
      authorized = true;
    } else if (user == null) {
      authorized = false;
      addReason("user doesn't exists");
    } else {
      boolean authorizedAction = user.getRole().canPerfomAction(action);
      if (!authorizedAction) addReason("Action " + action + " not authorized");
      boolean authorizedSpace = user.getRole().canBeInSpace(door);
      if (!authorizedSpace) addReason("Space not authorized");
      boolean authorizedTime = user.getRole().timeAlowed(now);
      if (!authorizedTime) addReason("Time not authorized");
      authorized = authorizedAction && authorizedSpace && authorizedTime;
    }
  }
}

