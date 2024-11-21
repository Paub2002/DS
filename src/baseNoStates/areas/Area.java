package baseNoStates.areas;

import baseNoStates.Door;
import baseNoStates.Visitor.Visitor;

import java.util.ArrayList;
/**
 * Abstract Area class
 * this is part of a composite pattern involving Aream, Partition, Space.
 * Abstract class used to access methods of partitions and spaces
 * */
public abstract class Area{

    protected String id; // Area id. Used to retrieve areas from Area tree. See: DirectoryArea
    private Space parent;// Parent Space, not used yet.

    public Area( String id, Space parent){//Super constructor. Initializes members.
        this.id = id;
        this.parent =parent;
    }
    //Get the area id.
    public String getId(){ return id;}
    public abstract ArrayList<Door> getDoorsGivingAccess();
    //Accepts a visitor to perform operations on this Area.
    public abstract void accept(Visitor visitor);
}

