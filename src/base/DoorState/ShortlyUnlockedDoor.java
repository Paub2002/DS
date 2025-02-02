package base.DoorState;

import base.Door;
import base.DoorObserver;

public class ShortlyUnlockedDoor extends DoorState {
  // Shortly Unlocked Door State. Implements DoorState State Pattern
  public ShortlyUnlockedDoor(Door d) {
    super(d);
    name = "unlocked_shortly";
    d.addObserver(new DoorObserver(d));
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
    return new UnlockedDoor(door);
  }

  @Override
  public DoorState unlock_shortly() {
    return this;
  }

}