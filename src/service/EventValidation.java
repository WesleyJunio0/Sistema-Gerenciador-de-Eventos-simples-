package service;
import Domain.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventValidation {
    private HashSet<Event> eventoUnico = new HashSet<>();
    private Event[] reposEvents;


    public EventValidation(Event[] events){
        this.reposEvents = events;
        this.events();
    }


    public HashSet<Event> getEventoUnico() {
        return eventoUnico;
    }



    public static boolean isdateLocal(  LocalDateTime args) throws DateTimeParseException {
        LocalDateTime date = LocalDateTime.now();

        if (args.isBefore(date)) {
            return true;
        }

        return false;
    }


    public void events() throws DateTimeParseException {

        for(int i = 0; i < reposEvents.length; i++){

            if (reposEvents[i].getName().isEmpty() || reposEvents[i].getName() == null){
                System.out.println("Error: Event name cannot be empty");
                return;
            }
            if (reposEvents[i].getDuration() <= 0){
                System.out.println("Error: Duration must be positive");
                return;
            }

            if (eventoUnico.contains(reposEvents[i].toString())){
                System.out.println("Error: Event \""+ reposEvents[i].toString() +"\" already exists");
                return;

            }
            if (isdateLocal(reposEvents[i].getDateTime())){
                System.out.println("Error: Event cannot be in the past");
                return;
            }

            eventoUnico.add(reposEvents[i]);
            System.out.println("Event added: "+reposEvents[i].toString());

        }

    }


}
