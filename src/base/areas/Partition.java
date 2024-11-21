package base.areas;

import base.Door;

import java.util.ArrayList;

public class Partition extends Area {
  private final ArrayList<Door> in;
  private final ArrayList<Door> out;

    public Partition(String id, Space Parent)
    {
        super(id,Parent);
        this.out= new ArrayList<>();
        this.in = new ArrayList<>();
        Parent.addChild(this);
    }

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
