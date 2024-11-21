package baseNoStates.areas;

import baseNoStates.Door;
import baseNoStates.Visitor.Visitor;

import java.util.ArrayList;

public class Space extends Area {
    private final ArrayList<Area> Child_Areas;

    public Space(String id, Space Parent) {
        super(id,Parent);
        this.Child_Areas = new ArrayList<Area>();
        if (Parent != null )
        {
            Parent.addChild(this);
        }

    }
    public void addChild(Area child){ this.Child_Areas.add(child); }
    @Override
    public ArrayList<Door> getDoorsGivingAccess() {
        ArrayList<Door> result = new ArrayList<>();
        for (Area child : Child_Areas) {
            result.addAll(child.getDoorsGivingAccess());
        }
        return result;
    }
    // Accepts a visitor to perform operations on this Space
    @Override
    public void accept(Visitor visitor) {
        visitor.visitSpace(this);
        for (Area child : Child_Areas) {
            child.accept(visitor);
        }
    }
}
