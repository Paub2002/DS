package baseNoStates.areas;

import baseNoStates.Door;

import java.util.ArrayList;
public abstract class Area{

    protected String id;
    private Space parent;

    public Area( String id, Space parent)             {
        this.id = id;
        this.parent =parent;
    }
    public String getId()               { return id;}

    public abstract ArrayList<Partition> getPartitions();
    public abstract ArrayList<Door> getDoorsGivingAccess();
    public abstract Area findAreaById( String find_id);
    public abstract Partition findPartitionById( String find_id);
}

