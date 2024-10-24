package baseNoStates.DoorState;

import baseNoStates.Door;

public class LockedDoor extends DoorState {
    // Locked Door State. Implements DoorState State Pattern
    LockedDoor(Door door) {
        super(door);
        name = "locked";
    }

    @Override
    public boolean open() {
        return true;
    }

    @Override
    public boolean close() {
        return true;
    }

    @Override
    public DoorState lock() {
        return this;
    }

    @Override
    public DoorState unlock() {
        return new UnlockedDoor(door);
    }

    @Override
    public DoorState unlock_shortly() {
        return new ShortlyUnlockedDoor(door);
    }

}