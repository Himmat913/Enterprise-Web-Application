import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class Q4_EventManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter event name: ");
        String name = sc.nextLine();

        System.out.print("Enter event date (yyyy-MM-dd): ");
        String dateInput = sc.nextLine();

        System.out.print("Enter start time (HH:mm): ");
        String timeInput = sc.nextLine();

        System.out.print("Enter venue (press Enter to skip): ");
        String venueInput = sc.nextLine();

        LocalDate eventDate;
        LocalTime startTime;
        try {
            eventDate = LocalDate.parse(dateInput);
            startTime = LocalTime.parse(timeInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format");
            sc.close();
            return;
        }

        Optional<String> venue;
        if (venueInput.isBlank()) {
            venue = Optional.empty();
        } else {
            venue = Optional.of(venueInput);
        }

        System.out.println();
        System.out.println("Event Details");
        System.out.println("Name: " + name);
        System.out.println("Date: " + eventDate);
        System.out.println("Start time: " + startTime);
        System.out.println("Venue: " + venue.orElse("Venue not specified"));

        LocalDate today = LocalDate.now();
        if (eventDate.isAfter(today)) {
            System.out.println("The event is in the future");
        } else if (eventDate.isEqual(today)) {
            System.out.println("The event is today");
        } else {
            System.out.println("The event is in the past");
        }

        long daysLeft = ChronoUnit.DAYS.between(today, eventDate);
        if (daysLeft >= 0) {
            System.out.println("Days remaining: " + daysLeft);
        } else {
            System.out.println("The event was " + Math.abs(daysLeft) + " days ago");
        }

        LocalTime endTime = startTime.plusHours(2);
        System.out.println("End time: " + endTime);

        sc.close();
    }
}
