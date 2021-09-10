package assignement1;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateTimeAPI {
    public static LocalDate lastThursday(final LocalDate date) {
        final DayOfWeek dayOfWeekObj = date.getDayOfWeek();
        final int dayOfWeek = dayOfWeekObj.getValue();
        final int daysSinceLastThursday = dayOfWeek > 4 ? dayOfWeek % 4 : dayOfWeek + 7 - 4;
        return date.minusDays(daysSinceLastThursday);
    }

    public static List<Integer> monthLengthsByYear(final int year) {
        return IntStream.rangeClosed(1, 12).boxed().map(m -> LocalDate.of(year, m, 01).lengthOfMonth())
                .collect(Collectors.toList());
    }

    public static List<LocalDate> allMondaysByMonthThisYear(final Month month) {
        final int currentYear = LocalDate.now().getYear();

        List<LocalDate> dates = new LinkedList<LocalDate>();
        LocalDate date = LocalDate.of(currentYear, month, 01).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        Month currentMonth = month;
        while (currentMonth == month) {
            dates.add(date);
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            currentMonth = date.getMonth();
        }
        return dates;
    }

    public static boolean isFridayThe13th(final LocalDate date) {
        return date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    public static void main(final String[] _args) {
        System.out.println("1)");
        final LocalDateTime birthday = LocalDateTime.of(1998, 2, 19, 23, 45);
        System.out.println("Birthday: " + birthday);
        System.out.println();

        System.out.println("2)");
        final int hundredYears = 100 * 365;
        final LocalDate randomDate = LocalDate
                .ofEpochDay(ThreadLocalRandom.current().nextInt(-hundredYears, hundredYears));
        System.out.println("Random Date: " + randomDate);
        System.out.println("Last Thursday: " + lastThursday(randomDate));
        System.out.println();

        System.out.println("3)");
        // Specifies the time zone id such as "US/Pacific"
        final ZoneId zone = ZoneId.of("US/Pacific");
        // Specifies the time offset from "Greenwich/UTC"
        // ZoneId's reference ZoneOffset instances
        // (e.g. The ZoneOffset for US/Pacific is UTC-8)
        final ZoneOffset zoneOffset = zone.getRules().getOffset(LocalDateTime.now());
        System.out.println(zone + " offset now: " + zoneOffset);
        System.out.println();

        System.out.println("4)");
        final Instant instant = Instant.now();
        System.out.println("Instant Now: " + instant);
        System.out.println("As ZonedDateTime: " + instant.atZone(zone));
        final ZonedDateTime zonedDateTime = ZonedDateTime.now(zone);
        System.out.println("ZonedDateTime Now: " + zonedDateTime);
        System.out.println("As ZonedDateTime: " + zonedDateTime.toInstant());
        System.out.println();

        System.out.println("5)");
        final int year = 2021;
        System.out.println("Number of days in each month for " + year);
        System.out.println(Arrays.toString(monthLengthsByYear(year).toArray()));
        System.out.println();

        System.out.println("6)");
        System.out.println("Mondays in the month of " + Month.APRIL + ":");
        System.out.println(Arrays.toString(DateTimeAPI.allMondaysByMonthThisYear(Month.APRIL).toArray()));
        System.out.println();

        System.out.println("7)");
        System.out.println("Random Date: " + randomDate);
        System.out.println(DateTimeAPI.isFridayThe13th(randomDate) ? "Is" : "Isn't" + " Friday the 13th");
        System.out.println();
    }
}