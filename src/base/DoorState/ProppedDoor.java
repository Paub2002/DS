package base.DoorState;
import base.Door;

public class ProppedDoor extends DoorState {
    // Propped Door State. Implements DoorState State Pattern

    public ProppedDoor(Door d) {
        super(d);
        name = "propped";
    }
    public boolean open() {
        return false;
    }



    @Override
    public DoorState lock() {
        return null;
    }

    @Override
    public DoorState unlock() {
        return null;
    }

    @Override
    public DoorState unlock_shortly() {
        return null;
    }

}