package baseNoStates.DoorState;
import baseNoStates.Door;

public class ShortlyUnlockedDoor extends DoorState {

    public ShortlyUnlockedDoor(Door d) {
        super(d);
        name = "unlocked_shortly";
    }
    @Override
    public boolean open() {
        return false;
    }

    @Override
    public boolean close() {
        return true;
    }

    @Override
    public DoorState lock() {
        return new LockedDoor(door);
    }

    @Override
    public DoorState unlock() {
        return new UnlockedDoor(door);
    }

    @Override
    public DoorState unlock_shortly() {
        return this;
    }

}