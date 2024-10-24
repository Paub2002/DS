package baseNoStates.DoorState;
import baseNoStates.Door;
public abstract class DoorState {
    //Abstract class to define door states. Uses a state pattern.
    Door door;
    String name;

    public DoorState(Door d){
        door = d;
    }
    public String getName() { return name; }

// Different Actions we can do on a door,
    abstract public boolean open();
    abstract public boolean close();
    abstract public DoorState lock();
    abstract public DoorState unlock();
    abstract public DoorState unlock_shortly();

}
