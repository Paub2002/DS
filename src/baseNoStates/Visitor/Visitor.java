package baseNoStates.Visitor;
import baseNoStates.areas.*;

public interface Visitor
{
    // Method for visiting Space objects
    void visitSpace(Space space);
    // Method for visiting Partition objects
    void visitPartition(Partition partition);
}
