package baseNoStates;

import baseNoStates.DoorState.LockedDoor;
import baseNoStates.DoorState.ProppedDoor;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

public class DoorObserver implements Observer {

    Door door;
    Timer timer;
    public DoorObserver(Door door) {
        this.door = door;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (! door.isClosed())
                {
                    door.setState( new ProppedDoor(door));
                }
            }
        };
        timer.schedule(task, 10000);
    }
    @Override
    public void update(Observable o, Object arg) {
            this.door.setState(new LockedDoor(door));
            this.door.deleteObserver(this);
    }
}
