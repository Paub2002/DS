package baseNoStates.Visitor;
import baseNoStates.areas.*;

import java.util.ArrayList;

public class VisitorSpaceList implements Visitor
{
    private final ArrayList<Space> spaces = new ArrayList<>();

    // Visit method for Space. Adds the space to the list
    @Override
    public void visitSpace(Space space)
    {
        spaces.add(space);
    }

    // Visit method for Partition. Does nothing as we are only interested in spaces
    @Override
    public void visitPartition(Partition partition)
    {
    }

    public ArrayList<Space> getSpaces()
    {
        return spaces;
    }
}
