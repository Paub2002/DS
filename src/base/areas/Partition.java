package base.areas;

import base.Door;
import base.Visitor.Visitor;

import java.util.ArrayList;

public class Partition extends Area {
  private final ArrayList<Door> in;
  private final ArrayList<Door> out;

  public Partition(String id, Space Parent) {
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
}
