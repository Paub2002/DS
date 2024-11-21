package base;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private final Integer startYear;  // Año de inicio
    private final Integer endYear;    // Año de finalización
    private final Integer startMonth; // Mes de inicio
    private final Integer endMonth;   // Mes de finalización
    private final ArrayList<Integer> allowedDays; // Días permitidos de la semana (1=lunes, ..., 7=domingo)
    private final Integer startHour;  // Hora de inicio (puede ser null)
    private final Integer endHour;    // Hora de finalización (puede ser null)
    public Schedule(Integer startYear, Integer endYear, Integer startMonth, Integer endMonth, Integer startHour, Integer endHour, List<Integer> allowedDays)
    {
        this.startYear = startYear;
        this.endYear = endYear;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.startHour = startHour;
        this.endHour = endHour;
        this.allowedDays = new ArrayList<>( allowedDays);
    }
    public boolean canSendRequests(LocalDateTime now) {

        int Year =  now.getYear();
        int Month = now.getMonthValue();
        int Hour = now.getHour();
        int Dow = now.getDayOfWeek().getValue();
        if ( startMonth == null||endMonth == null || startYear == null || endYear == null ) return false;
        boolean timeAllowed = (Year > startYear && Year < endYear) || ( Year == startYear && Month >= startMonth) ||  (Year == endYear && Month <= endMonth );
        // Checks that the  time is within the range of hours, if any
        boolean hourAllowed = (startHour == null && endHour == null ) || ( Hour >= startHour && Hour <= endHour);
        // Checks that today's day of the week is in the weekly work days of the role
        boolean dayAllowed = allowedDays.isEmpty() || allowedDays.contains(Dow);
        return timeAllowed && hourAllowed && dayAllowed;
    }
}
