package baseNoStates;

import java.util.ArrayList;
abstract class Area{

    private final String id;

    public Area(string id)              { this.id = id; }
    public String getId()               { return id;}

    public abstract function Space getSpaces();
    public abstract function ArrayList<Door> getDoorsGivingAcces();
    public abstract function Area findAreaById(string find_id){

        if (id == find_id ) {
            return this;
        }
        for ( Area a : Child_Areas ){
            if ((val = Area.findAreaById(find_id)) != Null){return val;}
        }
        return Null;
    }
}
class AreaGroup extends Area{
    private final AreaGroup parent;
    private final   ArrayList<Area> Child_Areas;

    public AreaGroup(String id, AreaGroup Parent = null) {
        if (Parent != null )
        {
            parent = Parent;
            Parent.addChild(this);
        }

    }
    public  void    addChild(Area child)    { this.Child_Areas.add(child); }

    public ArrayList<Door> getDoorsGivingAcces(){
        ArrayList<Door> result =new ArrayList<Door>();
        for ( Area a : Child_Areas )
        {
            result.addAll(a.getDoorsGivingAcces());
        }

    }

}
class Space extends Area{
    private final ArrayList<Door> in, out;

    public void AddinDoor(Door in)     { in.Add(in);}
    public void AddoutDoor(Door out)   { out.Add(out);}

    public ArrayList<Door> getDoorsGivingAcces() { return in; }

}