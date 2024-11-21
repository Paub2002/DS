package base.DoorState;
import base.Door;
public abstract class DoorState {
    //Abstract class to define door states. Uses a state pattern.
    final Door door;
    String name;

    public DoorState(Door d){
        door = d;
    }
    public String getName() { return name; }

    public boolean close(){return true; }

// Different Actions we can do on a door,
    abstract public boolean open();
    abstract public DoorState lock();
    abstract public DoorState unlock();
    abstract public DoorState unlock_shortly();

}
