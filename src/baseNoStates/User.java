package baseNoStates;

public class User {
  private final String name;
  private final String credential;

  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public String getCredential() {
    return credential;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}

/*
package baseNoStates;

public class Role
{
  private String name;
  private List<String> allowedActions;  // Ej. [lock, unlock_shortly]
  private List<String> allowedPlaces;    // Ej. [D1, D2] o áreas
  private Integer startYear;  // Año de inicio
  private Integer endYear;    // Año de finalización
  private Integer startMonth; // Mes de inicio
  private Integer endMonth;   // Mes de finalización
  private List<Integer> allowedDays; // Días permitidos de la semana (1=lunes, ..., 7=domingo)
  private Integer startHour;  // Hora de inicio (puede ser null)
  private Integer endHour;    // Hora de finalización (puede ser null)

  public Role(String name, List<String> allowedActions, List<String> allowedPlaces, Integer startYear, Integer endYear, Integer startMonth, Integer endMonth, Integer startHour, Integer endHour, List<Integer> allowedDays)
  {
    this.name = name;
    this.allowedActions = allowedActions;
    this.allowedPlaces = allowedPlaces;
    this.startYear = startYear;
    this.endYear = endYear;
    this.startMonth = startMonth;
    this.endMonth = endMonth;
    this.startHour = startHour;
    this.endHour = endHour;
    this.allowedDays = allowedDays;
  }

  public boolean canPerformAction(String action, String PlaceId, Integer currentYear, Integer currentMonth, Integer currentDay, Integer currentHour, Integer currentDayOfWeek)
  {
    // Es mira si aquest rol conté l'acció requerida i la porta especificada
    boolean actionAllowed = allowedActions.contains(action) && allowedPlaces.contains(PlaceId);
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


public class User {
  private final String name;
  private final String credential;
  private final Role role;

  public User(String name, String credential, Role role) {
    this.name = name;
    this.credential = credential;
    this.role = role;
  }

  public String getCredential() {
    return credential;
  }
  public Role getRole() { return role;}

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + ", role=" + role.getName() + "}";
  }
}


*/
