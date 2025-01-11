package base.Visitor;

import base.areas.Partition;
import base.areas.Space;

public interface Visitor {
  // Method for visiting Space objects
  void visitSpace(Partition Partition);

  // Method for visiting Partition objects
  void visitPartition(Space space);
}
