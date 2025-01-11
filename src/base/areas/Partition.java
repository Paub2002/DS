package base.areas;

import base.Door;
import base.Visitor.Visitor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Partition extends Area {
  public final ArrayList<Area> Child_Areas;

  public Partition(String id, Partition Parent) {
    super(id, Parent);
    this.Child_Areas = new ArrayList<>();
    if (Parent != null) {
      Parent.addChild(this);
    }

  }

  public void addChild(Area child) {
    this.Child_Areas.add(child);
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> result = new ArrayList<>();
    for (Area child : Child_Areas) {
      result.addAll(child.getDoorsGivingAccess());
    }
    return result;
  }

  // Accepts a visitor to perform operations on this Space
  @Override
  public void accept(Visitor visitor) {
    visitor.visitSpace(this);
    for (Area child : Child_Areas) {
      child.accept(visitor);
    }
  }
  @Override
  public JSONObject toJson(int depth) {
    // for depth=1 only the root and children,
    // for recusive = all levels use Integer.MAX_VALUE
    JSONObject json = new JSONObject();
    json.put("class", "partition");
    json.put("id", id);
    JSONArray jsonAreas = new JSONArray();
    if (depth > 0) {
      for (Area a : Child_Areas) {
        jsonAreas.put(a.toJson(depth - 1));
      }
      json.put("areas", jsonAreas);
    }
    return json;
  }
}