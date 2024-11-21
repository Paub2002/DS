package base.Visitor;
import base.Door;
import base.areas.*;

import java.util.ArrayList;

public class VisitorListPartitionDoors implements Visitor
{
    private final ArrayList<Door> doors = new ArrayList<>();

    // Visit method for Space. It doesn't do anything since doors are only in Partition
    @Override
    public void visitSpace(Space space){
    }

    // Visit method for Partition. Adds all doors from the partition to the list
    @Override
    public void visitPartition(Partition partition) {
        doors.addAll(partition.getDoorsGivingAccess());
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }
}
