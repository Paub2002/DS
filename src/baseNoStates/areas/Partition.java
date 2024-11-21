package baseNoStates.areas;

import baseNoStates.Door;
import baseNoStates.Visitor.Visitor;

import java.util.ArrayList;

public class Partition extends Area {
    private ArrayList<Door> in, out;

    public Partition(String id, Space Parent)
    {
        super(id,Parent);
        this.out= new ArrayList<Door>();
        this.in = new ArrayList<Door>();
        Parent.addChild(this);
    }
    public void AddinDoor(Door new_in)     { this.in.add(new_in);}
    public void AddoutDoor(Door new_out)   { this.out.add(new_out);}

    public void addInDoor(Door new_in)    { this.in.add(new_in); }
    public void addOutDoor(Door new_out)    { this.in.add(new_out); }

    @Override
    public ArrayList<Door> getDoorsGivingAccess() {
        return new ArrayList<>(in);
    }
    //Accepts a visitor to perform operations on this Partition
    public void accept(Visitor visitor) {
        visitor.visitPartition(this);
    }
}
