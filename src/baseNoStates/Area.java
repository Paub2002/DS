package baseNoStates;

import java.util.ArrayList;
public abstract class Area{

    protected String id;
    private  Space parent;

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
class Space extends Area{
    private  ArrayList<Area> Child_Areas;

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
        this.Child_Areas = new ArrayList<Area>();
        if (Parent != null )
        {
            Parent.addChild(this);
        }

    }
    public  void    addChild(Area child)    { this.Child_Areas.add(child); }

    public ArrayList<Door> getDoorsGivingAccess(){
        ArrayList<Door> result =new ArrayList<Door>();
        for ( Area a : Child_Areas )
        {
            result.addAll(a.getDoorsGivingAccess());
        }
        return result;
    }

    @Override
    public ArrayList<Partition> getPartitions() {
        ArrayList<Partition> result =new ArrayList<Partition>();
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
class Partition extends Area{
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