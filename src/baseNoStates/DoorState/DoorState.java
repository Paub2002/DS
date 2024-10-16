package baseNoStates.DoorState;
import baseNoStates.Door;
public abstract class DoorState {

    Door door;
    String name;

    public DoorState(Door d){
        door = d;
    }
    public String getName() { return name; }

    abstract public boolean open();
    abstract public boolean close();
    abstract public DoorState lock();
    abstract public DoorState unlock();
    abstract public DoorState unlock_shortly();

}
