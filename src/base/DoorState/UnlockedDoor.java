package base.DoorState;


import base.Door;

public class UnlockedDoor extends DoorState {
  //Unlocked Door State. Implements DoorState State Pattern

  public UnlockedDoor(Door d) {
    super(d);
    name = "unlocked";

  }

  @Override
  public boolean open() {

    return false;
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