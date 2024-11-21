package base;

import base.Visitor.VisitorPartitionList;
import base.areas.Area;
import base.areas.Partition;
import base.areas.Space;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Role {
  private final String name;
  private final ArrayList<String> allowedActions;  // Ej. [lock, unlock_shortly]
  private final ArrayList<String> allowedSpaces;    // Ej. [parking, hall]
  private final Schedule schedule; //Scheduler class, used to determine whether a user can be in a space

  public Role(String name, List<String> allowedActions, List<String> allowedSpacesAreas, Integer startYear, Integer endYear, Integer startMonth, Integer endMonth, Integer startHour, Integer endHour, List<Integer> allowedDays) {
    this.name = name;
    this.allowedActions = new ArrayList<>(allowedActions);
    DirectoryAreas directoryAreas = DirectoryAreas.getInstance();
    ArrayList<String> spaces = new ArrayList<>();
    for (String s : allowedSpacesAreas) {
      Area area = directoryAreas.findAreaById(s);
      if (area instanceof Space) {
        VisitorPartitionList visitorSpaceList = new VisitorPartitionList();
        area.accept(visitorSpaceList);
        ArrayList<Partition> spaceList = visitorSpaceList.getPartitions();
        for (Partition space : spaceList) {
          spaces.add(space.getId());
        }
      } else if (area instanceof Partition) {
        spaces.add(area.getId());
      }
    }
    this.allowedSpaces = new ArrayList<>(spaces);
    this.schedule = new Schedule(startYear, endYear, startMonth, endMonth, startHour, endHour, allowedDays);
  }

  public boolean canPerfomAction(String action) {
    return allowedActions.contains(action);
  }

  public boolean timeAlowed(LocalDateTime now) {
    return this.schedule.canSendRequests(now);
  }

  public boolean canBeInSpace(Door Target) {
    DirectoryAreas directoryAreas = DirectoryAreas.getInstance();
    // Retrieve from and to spaces
    Partition from = Target.getFromSpace();
    Partition to = Target.getToSpace();

    Logger logger = LoggerFactory.getLogger(this.getClass());
    logger.info(allowedSpaces.toString());
    return allowedSpaces.contains(from.getId()) && allowedSpaces.contains(to.getId());
  }

  public String getName() {
    return name;
  }
}
