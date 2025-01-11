package base.Visitor;

import base.Door;
import base.areas.Space;
import base.areas.Partition;

import java.util.ArrayList;

public class VisitorListPartitionDoors implements Visitor {
  private final ArrayList<Door> doors = new ArrayList<>();

  // Visit method for Space. It doesn't do anything since doors are only in Partition
  @Override
  public void visitSpace(Partition Partition) {
  }

  // Visit method for Partition. Adds all doors from the partition to the list
  @Override
  public void visitPartition(Space space) {
    doors.addAll(space.getDoorsGivingAccess());
  }

  public ArrayList<Door> getDoors() {
    return doors;
  }
}
