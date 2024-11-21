package base.Visitor;
import base.areas.*;

import java.util.ArrayList;

public class VisitorPartitionList implements Visitor
{
    private final ArrayList<Partition> partitions = new ArrayList<>();

    // Visit method for Space. Adds the space to the list
    @Override
    public void visitSpace(Space space)
    {
    }

    // Visit method for Partition. Does nothing as we are only interested in spaces
    @Override
    public void visitPartition(Partition partition)
    {
      partitions.add(partition);
    }

    public ArrayList<Partition> getPartitions()
    {
        return partitions;
    }
}
