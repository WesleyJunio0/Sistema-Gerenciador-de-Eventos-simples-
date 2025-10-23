package Domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event {
    private String name;
    private int duration;
    private LocalDateTime dateTime;

    public Event(String name, int duration, String dateTime) throws DateTimeParseException,IllegalArgumentException {
        this.name = name;
        this.duration = duration;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.dateTime = LocalDateTime.parse(dateTime,formatter);
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return name + ' ' +
                "ás " + duration +
                " do dia " + dateTime.format(formatter);
    }
}
