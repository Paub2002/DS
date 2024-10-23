package baseNoStates.areas;

import baseNoStates.Door;

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

    @Override
    public ArrayList<Door> getDoorsGivingAccess() { return in; }
    @Override
    public Area findAreaById(String find_id) {
        return this.id.equals(find_id) ? this : null;
    }
    @Override
    public ArrayList<Partition> getPartitions() {
        ArrayList<Partition> result =new ArrayList<>();
        result.add(this);
        return result;
    }

    @Override
    public Partition findPartitionById(String find_id) {
        return this.id.equals(find_id) ? this : null;
    }

    public void addInDoor(Door new_in)    { this.in.add(new_in); }
    public void addOutDoor(Door new_out)    { this.in.add(new_out); }
}
