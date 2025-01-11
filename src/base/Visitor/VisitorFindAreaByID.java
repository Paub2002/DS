package base.Visitor;

import base.areas.Area;
import base.areas.Partition;
import base.areas.Space;

public class VisitorFindAreaByID implements Visitor {
  private String targetId = "";
  private Area foundArea = null;

  public VisitorFindAreaByID(String targetId) {
    this.targetId = targetId;
    this.foundArea = null;
  }

  // Visit method for Space. If the ID matches, store the found Space
  @Override
  public void visitSpace(Partition Partition) {
    if (Partition.getId().equals(targetId)) {
      foundArea = Partition;
    }
  }

  // Visit method for Partition. If the ID matches, store the found Partition
  @Override
  public void visitPartition(Space space) {
    if (space.getId().equals(targetId)) {
      foundArea = space;
    }
  }

  public Area getFoundArea() {
    return foundArea;
  }
}
