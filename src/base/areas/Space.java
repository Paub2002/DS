package base.areas;

import base.Door;
import base.Visitor.Visitor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Space extends Area {
  private final ArrayList<Door> in;
  private final ArrayList<Door> out;

  public Space(String id, Partition Parent) {
    super(id, Parent);
    this.out = new ArrayList<>();
    this.in = new ArrayList<>();
    Parent.addChild(this);
  }

  public void addInDoor(Door new_in) {
    this.in.add(new_in);
  }

  public void addOutDoor(Door new_out) {
    this.in.add(new_out);
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return new ArrayList<>(in);
  }

  //Accepts a visitor to perform operations on this Partition
  @Override
  public void accept(Visitor visitor) {
    visitor.visitPartition(this);
  }

  public JSONObject toJson(int depth) { // depth not used here
    JSONObject json = new JSONObject();
    json.put("class", "space");
    json.put("id", id);
    JSONArray jsonDoors = new JSONArray();
    for (Door d : in) {
      jsonDoors.put(d.toJson());
    }
    json.put("access_doors", jsonDoors);
    return json;
  }

}
