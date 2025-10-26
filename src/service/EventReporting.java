package service;
import Domain.Event;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventReporting {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");

    public static StringBuilder reporting(HashSet<Event> args) {
        StringBuilder message =  new StringBuilder();
        if (args.isEmpty()) {
            System.out.println(args.isEmpty());
            message.append("Nenhum evento registrado");
            return message;
        }

        message.append(" ==== Event Report ====\n");
        for (Event i: args){
            message.append(String.format("%s - %s (%s minutes)\n",i.getDateTime().format(FORMATTER),
                    i.getName(), i.getDuration()));
        }

        return message;

    }

    public static StringBuilder summary(Event event, HashSet<Event> events) {
        StringBuilder report = new StringBuilder();
        if (event == null){
           report.append("No events to display");
           return report;
        }

        report.append(" ===== Event Summary =====\n");
        report.append(String.format("Total event: %s\nNext event: %s (%s)", events.size(), event.getName(),
                event.getDateTime().format(FORMATTER)));
        return report;

    }

    public static StringBuilder daily(String date, List<Event> list ) {
        StringBuilder report = new StringBuilder();

        if (list.isEmpty()) {
            report.append("Nenhum evento registrado");
            return report;
        }

        if (date.isEmpty()){
            report.append("formated invalid");
            return report;
        }
        report.append(String.format(" ===== Events on %s =====\n",date));
        for (Event i : list){
                report.append(String.format("%s - %s (%s minutes)\n",i.getDateTime().format(FORMATTER),
                        i.getName(), i.getDuration()));
        }

        return report;
    }


}
