package base.Visitor;

import base.areas.*;

public class VisitorFindAreaByID implements Visitor
{
    private String targetId = "";
    private Area foundArea = null;
    public VisitorFindAreaByID(String targetId) {
        this.targetId = targetId;
        this.foundArea = null;
    }
    // Visit method for Space. If the ID matches, store the found Space
    @Override
    public void visitSpace(Space space) {
        if (space.getId().equals(targetId)) {
            foundArea = space;
        }
    }
    // Visit method for Partition. If the ID matches, store the found Partition
    @Override
    public void visitPartition(Partition partition) {
        if (partition.getId().equals(targetId)) {
            foundArea = partition;
        }
    }

    public Area getFoundArea() {
        return foundArea;
    }
}
