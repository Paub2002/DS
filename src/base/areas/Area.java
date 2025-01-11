package base.areas;

import base.Door;
import base.Visitor.Visitor;

import java.util.ArrayList;
import org.json.JSONObject;
/**
 * Abstract Area class
 * this is part of a composite pattern involving Area, Partition, Space.
 * Abstract class used to access methods of partitions and spaces
 */
public abstract class Area {

  protected final String id; // Area id. Used to retrieve areas from Area tree. See: DirectoryArea
  private final Partition parent;// Parent Space, not used yet.

  public Area(String id, Partition parent) {//Super constructor. Initializes members.
    this.id = id;
    this.parent = parent;
  }

  //Get the area id.
  public String getId() {
    return id;
  }

  public abstract ArrayList<Door> getDoorsGivingAccess();

  //Accepts a visitor to perform operations on this Area.
  public abstract void accept(Visitor visitor);
  public abstract JSONObject toJson(int depth);
}

