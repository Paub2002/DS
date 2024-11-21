package base.areas;

import base.Door;

import java.util.ArrayList;
/**
 * Abstract Area class
 * this is part of a composite pattern involving Area, Partition, Space.
 * Abstract class used to access methods of partitions and spaces
 * */
public abstract class Area{

    protected final String id; // Area id. Used to retrieve areas from Area tree. See: DirectoryArea
    private final Space parent;// Parent Space, not used yet.

    public Area( String id, Space parent){//Super constructor. Initializes members.
        this.id = id;
        this.parent =parent;
    }
    //Get the area id.
    public String getId(){ return id;}

    //Get all areas Partition ( leafs of the areas tree)
    public abstract ArrayList<Partition> getPartitions();
    //Get all doors that lead to the area
    public abstract ArrayList<Door> getDoorsGivingAccess();
    //Search for a child area in the areas three
    public abstract Area findAreaById( String find_id);
    //Search for a child partition (leaf )in the areas three. Used to determine if a user can access somewhere.
    public abstract Partition findPartitionById( String find_id);
}

