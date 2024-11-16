package baseNoStates;

import baseNoStates.areas.Partition;
import baseNoStates.areas.Area;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Role
{
  private final String name;
  private final ArrayList<String> allowedActions;  // Ej. [lock, unlock_shortly]
  private final ArrayList<String> allowedSpaces;    // Ej. [parking, hall]
  private final Schedule schedule; //Scheduler class, used to determine whether a user can be in a space

  public Role(String name, List<String> allowedActions, List<String> allowedSpaces, Integer startYear, Integer endYear, Integer startMonth, Integer endMonth, Integer startHour, Integer endHour, List<Integer> allowedDays)
  {
    this.name = name;
    this.allowedActions = new ArrayList<String>( allowedActions);
    this.allowedSpaces=  new ArrayList<String>( allowedSpaces) ;
    this.schedule = new Schedule(startYear,endYear,startMonth,endMonth,startHour,endHour,allowedDays);
  }
  public boolean canPerfomAction(String action){ return allowedActions.contains(action);}
  public boolean timeAlowed(LocalDateTime now) {return this.schedule.canSendRequests(now);}
  public boolean canBeInSpace( Door Target)
  {
    DirectoryAreas directoryAreas = DirectoryAreas.getInstance();
    // Retrieve from and to spaces
    Partition from = Target.getFromSpace();
    Partition to = Target.getToSpace();

    boolean allowedFrom = false;
    boolean allowedTo = false;
    // Search for the found spaces in the areas three.
    for (String allowedSpace : allowedSpaces) {
      Area alllowedArea = directoryAreas.findAreaById(allowedSpace);
      ArrayList<Partition> partitions = alllowedArea.getPartitions();
      if (partitions.contains(from)) {
        allowedFrom = true;
      }
      if (partitions.contains(to)) {
        allowedTo= true;
      }
      if (allowedFrom && allowedTo) {break; }
    }
    //Checks if this role contains the required action and the specified port
    return allowedFrom && allowedTo;
  }

  public String getName() {
    return name;
  }
}
