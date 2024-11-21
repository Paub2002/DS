package base.areas;

import base.Door;

import java.util.ArrayList;

public class Space extends Area {
    private final ArrayList<Area> Child_Areas;

    @Override
    public Area findAreaById( String find_id){

        if (id.equals(find_id )) {
            return this;
        }
        for ( Area a : this.Child_Areas ){
            Area child_find = a.findAreaById(find_id);
            if ( child_find != null){return child_find;}
        }
        return null;
    }
    public Space(String id, Space Parent) {
        super(id,Parent);
        this.Child_Areas = new ArrayList<>();
        if (Parent != null )
        {
            Parent.addChild(this);
        }

    }
    public  void    addChild(Area child)    { this.Child_Areas.add(child); }

    public ArrayList<Door> getDoorsGivingAccess(){
        ArrayList<Door> result = new ArrayList<>();
        for ( Area a : Child_Areas )
        {
            result.addAll(a.getDoorsGivingAccess());
        }
        return result;
    }

    @Override
    public ArrayList<Partition> getPartitions() {
        ArrayList<Partition> result = new ArrayList<>();
        for ( Area a : Child_Areas )
        {
            result.addAll(a.getPartitions());
        }
        return result;
    }
    public Partition findPartitionById( String find_id){
        for ( Area a : this.Child_Areas ){
            Partition child_find = a.findPartitionById(find_id);
            if ( child_find != null){return child_find;}
        }
        return null;
    }
}
