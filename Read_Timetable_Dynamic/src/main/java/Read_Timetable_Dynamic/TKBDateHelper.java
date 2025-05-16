package Read_Timetable_Dynamic;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TKBDateHelper {
    public static final LocalDate START_DATE = LocalDate.of(2025, 1, 13); // Ví dụ: 19/02/2024 là thứ 2 tuần 1

    public static int getWeekFromDate(LocalDate date) {
        long daysBetween = ChronoUnit.DAYS.between(START_DATE, date);
        return (int)(daysBetween / 7) + 1;
    }

    public static int getThuFromDate(LocalDate date) {
        int thu = date.getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday
        return thu + 1;
    }
}