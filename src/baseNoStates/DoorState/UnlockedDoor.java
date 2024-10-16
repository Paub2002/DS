package baseNoStates.DoorState;


import baseNoStates.Door;

public class UnlockedDoor extends DoorState {


    public UnlockedDoor(Door d) {
        super(d);
        name = "unlocked";

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
        return this;
    }

    @Override
    public DoorState unlock_shortly() {
        return new ShortlyUnlockedDoor(door);
    }

}