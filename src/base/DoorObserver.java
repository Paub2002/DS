package base;

import base.DoorState.LockedDoor;
import base.DoorState.ProppedDoor;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class DoorObserver implements Observer {
    // Door observer, when set looks for the door closing and updates its state.
    // Also checks after 10 seconds if the door has been closed and updates its state to propped or locked.

    final Door door; // Target door
    final Timer timer; // Timer to check for propped doors

    public DoorObserver(Door door) {
        this.door = door;
        timer = new Timer();
        TimerTask task = new TimerTask() { // Function that runs after 10 seconds to check if the door is close
            public void run() {
                if (! door.isClosed())
                {
                    door.setState( new ProppedDoor(door)); // if it isn't we set it to propped
                }
                else door.setState( new LockedDoor(door)); // if closed we set it to locked again
            }
        };
        timer.schedule(task, 10000); // Schedule after 10s
    }
    @Override
    public void update(Observable o, Object arg) {
            this.door.setState(new LockedDoor(door));
            this.door.deleteObserver(this); // When door is closed we delete the observer.
    }
}
