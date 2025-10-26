import Domain.Event;
import service.EventAnalysis;
import service.EventValidation;
import java.time.format.DateTimeParseException;
import java.util.List;
import static service.EventReporting.*;
import static service.EventAnalysis.*;
import static service.EventSearch.*;


public class Main {

    public static void main(String[] args) {
        // 1° command
        //"pattern [text]": Find events where name matches pattern
        //"date [yyyy-MM-dd]": Find events on specific date
        //"quit": End program
        String condicao = "date 2025-12-23";
        String[] parts = condicao.split(" ", 2);
        String command = parts[0];
        //2° Command
        //"between [event1] [event2]": Calculate time between two events
        //"overlap [date]": Find overlapping events on given date
        //"next": Find next upcoming event
        String condicao2 = "overlap 2025-12-23";
        String[] parts2 = condicao2.trim().split(" ");
        String command2 = parts2[0];
        //3° command
        //"report": Show summary of all events
        //"daily [date]": Show events for specific date
        //"summary": Show total events and next upcoming event
        String condicao3 = "daily 2025-12-23";
        String[] parts3 = condicao3.trim().split(" ");
        String command3 = parts3[0];

        try {
            System.out.println(" ====*====*==== Creation ====*====*====\n");
            Event[] events = { new Event("Trabalho",9,"2025-11-23 10:50"),
                    new Event("Trabalho",90,"2025-12-23 10:50"),
                    new Event("Trabalho",9,"2025-12-23 11:50")};

            System.out.println("\n ====*====*==== Validation ====*====*====\n");
            EventValidation test2 = new EventValidation(events);

            System.out.println("\n ====*====*==== Search ====*====*====\n");
            for(Event test: events){
                if(command.toLowerCase().equals("pattern")) {
                    padrao(parts[1], test.toString());
                } else if(command.equals("date")) {
                    padraoD(parts[1], test);
                } else {
                    System.out.println("Error: comando invalido");
                }

            }


            System.out.println("\n ====*====*==== Analysis ====*====*====\n");
            EventAnalysis analysis = new EventAnalysis(test2.getEventoUnico());
            List<Event> araa = padraoDArray(parts2[1], test2.getEventoUnico());
            if(command2.toLowerCase().equals("between")) {
                analisarDuration(events[0], events[1]);

            } else if(command2.toLowerCase().equals("overlap")) {
                System.out.println( analisarOverlap(araa));

            } else if(command2.equals("next")) {
                analysis.analisarNext();

            } else {
                System.out.println("Error: comando invalido");
            }

            System.out.println("\n ====*====*==== Reporting ====*====*====\n");
            if (command3.equals("report")){
                System.out.println(reporting(test2.getEventoUnico()));

            } else if (command3.equals("daily")) {
                System.out.println(daily(parts3[1], padraoDArray(parts3[1], test2.getEventoUnico())));

            } else if (command3.equals("summary")) {
                analysis.analisarNext();
                System.out.println(summary(analysis.nextEvent, test2.getEventoUnico()));

            } else {

            }

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format");
        }

    }
}