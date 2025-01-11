package base.Visitor;

import base.areas.Space;
import base.areas.Partition;

import java.util.ArrayList;

public class VisitorPartitionList implements Visitor {
  private final ArrayList<Space> spaces = new ArrayList<>();

  // Visit method for Space. Adds the space to the list
  @Override
  public void visitSpace(Partition Partition) {
  }

  // Visit method for Partition. Does nothing as we are only interested in spaces
  @Override
  public void visitPartition(Space space) {
    spaces.add(space);
  }

  public ArrayList<Space> getPartitions() {
    return spaces;
  }
}
