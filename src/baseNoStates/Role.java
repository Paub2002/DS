package baseNoStates;

import baseNoStates.areas.Partition;
import baseNoStates.areas.Area;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Role
{
  private final String name;
  private final ArrayList<String> allowedActions;  // Ej. [lock, unlock_shortly]
  private final ArrayList<String> allowedSpaces;    // Ej. [parking, hall]
  private final Integer startYear;  // Año de inicio
  private final Integer endYear;    // Año de finalización
  private final Integer startMonth; // Mes de inicio
  private final Integer endMonth;   // Mes de finalización
  private final ArrayList<Integer> allowedDays; // Días permitidos de la semana (1=lunes, ..., 7=domingo)
  private final Integer startHour;  // Hora de inicio (puede ser null)
  private final Integer endHour;    // Hora de finalización (puede ser null)

  public Role(String name, List<String> allowedActions, List<String> allowedSpaces, Integer startYear, Integer endYear, Integer startMonth, Integer endMonth, Integer startHour, Integer endHour, List<Integer> allowedDays)
  {
    this.name = name;
    this.allowedActions = new ArrayList<String>( allowedActions);
    this.allowedSpaces=  new ArrayList<String>( allowedSpaces) ;
    this.startYear = startYear;
    this.endYear = endYear;
    this.startMonth = startMonth;
    this.endMonth = endMonth;
    this.startHour = startHour;
    this.endHour = endHour;
    this.allowedDays = new ArrayList<>( allowedDays);
  }

  public boolean canPerformAction(String action, Door Target, Integer currentYear, Integer currentMonth, Integer currentDay, Integer currentHour, Integer currentDayOfWeek)
  {
    // Retrieve from and to spaces
    Partition from = Target.getFromSpace();
    Partition to = Target.getToSpace();

    boolean allowedFrom = false;
    boolean allowedTo = false;
    // Search for the found spaces in the areas three.
    for (String allowedSpace : allowedSpaces) {
      Area alllowedArea = DirectoryAreas.findAreaById(allowedSpace);
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
    boolean actionAllowed = allowedActions.contains(action) && allowedFrom && allowedTo;
    // It is ensured that the current date is between the start and end dates
    boolean timeAllowed = (currentYear != null && startYear != null && endYear != null && (currentYear > startYear && currentYear < endYear)) || (currentYear != null && startYear != null && currentYear.equals(startYear) && (currentMonth != null && currentMonth >= startMonth)) || (currentYear != null && endYear != null && currentYear.equals(endYear) && (currentMonth != null && currentMonth <= endMonth));
    // Checks that the current time is within the range of hours, if any
    boolean hourAllowed = ((startHour == null || endHour == null) || (currentHour != null && currentHour >= startHour && currentHour <= endHour));
    // Checks that today's day of the week is in the weekly work days of the role
    boolean dayAllowed = (allowedDays == null || allowedDays.contains(currentDayOfWeek));

    return actionAllowed && timeAllowed && hourAllowed && dayAllowed;
  }

  public String getName() {
    return name;
  }
}
