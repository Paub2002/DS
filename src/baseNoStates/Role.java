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
    Partition from = Target.getFromSpace();
    Partition to = Target.getToSpace();
    boolean allowedFrom = false;
    boolean allowedTo = false;
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
      // Es mira si aquest rol conté l'acció requerida i la porta especificada
    boolean actionAllowed = allowedActions.contains(action) && allowedFrom && allowedTo;
    // Per al següent mirem que existeixi any actual, any inici i any final,
    // si estem a any inici mirem que existeixi "mes" i que sigui major a mes inici,
    // si estem a any final mirem que existeixi "mes" i que sigui major a mes final
    boolean timeAllowed = (currentYear != null && startYear != null && endYear != null && (currentYear > startYear && currentYear < endYear)) || (currentYear != null && startYear != null && currentYear.equals(startYear) && (currentMonth != null && currentMonth >= startMonth)) || (currentYear != null && endYear != null && currentYear.equals(endYear) && (currentMonth != null && currentMonth <= endMonth));
    // Es mira que existeixi hora actual i hora inicial i final,
    // i que l'hora actual estigui entre la final i l'inicial
    boolean hourAllowed = ((startHour == null || endHour == null) || (currentHour != null && currentHour >= startHour && currentHour <= endHour));
    // Es mira si s'especifiquen dies (No en cas d'administrador, per exemple)
    // i mirem si el dia de la setmana present está en el rol.
    boolean dayAllowed = (allowedDays == null || allowedDays.contains(currentDayOfWeek));

    return actionAllowed && timeAllowed && hourAllowed && dayAllowed;
  }

  public String getName() {
    return name;
  }
}