package service;

import Domain.Event;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventUtils {
    private HashSet<String> eventoUnico = new HashSet<>();
    private Event[] events;


    public EventUtils(Event[] events){
        this.events = events;

    }


    public static void padrao(String padrao, String args) {
        //Encontrar padrão
        Pattern pattern = Pattern.compile(padrao+".*");
        Matcher matcher = pattern.matcher(args);
        if (matcher.matches()) {
            System.out.println( "Encontrado: "+ args);
        }

    }


    public static void padraoD(String padrao, String args) {
        //Encontrar padrão
        Pattern pattern = Pattern.compile(padrao);
        Matcher matcher = pattern.matcher(args);
        if (matcher.find()) {
            System.out.println( "Encontrado: "+ args);
        }

    }


    public List<Event> padraoDArray(String padrao) {
        //Encontrar padrão
        Pattern pattern = Pattern.compile(padrao);
        List<Event> list = new ArrayList<>();
        int cont = 0;
        for (int i = 0; i < events.length; i++){
            Matcher matcher = pattern.matcher(events[i].toString());
            if (matcher.find()) {
                list.add(events[i]);

                continue;
            } else {
                cont++;
                if (cont == events.length ){
                    System.out.println(" \"Error: Event not found\"");
                }
            }
        }

        return list;
    }


    public static String dateLocal( String name, int duration, String args) throws DateTimeParseException {
        if (name.isEmpty() || name == null) return"Error: Event name cannot be empty";
        if (duration <= 0) return "Error: Duration must be positive";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Duration du = Duration.ofMinutes(duration);
        LocalDateTime datepass = LocalDateTime.parse(args,formatter);
        LocalDateTime date = LocalDateTime.now();


        if (datepass.isBefore(date)) {
            return "Error: Event cannot be in the past";
        }

        return String.format("Valid event: %s at %s", name, datepass.format(formatter));
    }


    public static boolean isdateLocal(  LocalDateTime args) throws DateTimeParseException {
        LocalDateTime date = LocalDateTime.now();

        if (args.isBefore(date)) {
            return true;
        }

        return false;
    }


    public void events() throws DateTimeParseException {

        for(int i = 0; i < events.length; i++){

            if (events[i].getName().isEmpty() || events[i].getName() == null){
                System.out.println("Error: Event name cannot be empty");
                return;
            }
            if (events[i].getDuration() <= 0){
                System.out.println("Error: Duration must be positive");
                return;
            }

            if (eventoUnico.contains(events[i].toString())){
                System.out.println("Error: Event \""+ events[i].toString() +"\" already exists");
                return;

            }
            if (isdateLocal(events[i].getDateTime())){
                System.out.println("Error: Event cannot be in the past");
                return;
            }

            eventoUnico.add(events[i].toString());
            System.out.println("Event added: "+events[i].toString());

        }

    }


    public static void analizarDuration(Event eventTest1, Event eventTest2) {
        //Intervalo entre dois eventos
        LocalDateTime event1 = eventTest1.getDateTime();
        LocalDateTime event2 = eventTest2.getDateTime();
        Duration duration = Duration.between(event1, event2);

        System.out.println("Time between events: "+ eventTest1+ " "+duration.toHours()+" horas "
                +"e "+ eventTest2+" " + duration.toMinutes()+" minutos");


    }


    public static void analisarDt( List<Event> array) {
        //Overlap
        LocalDateTime timeStart;
        LocalDateTime timeend;
        LocalDateTime timeStart2;
        LocalDateTime timeend2;

        outer:for(Event i : array){
            timeStart = i.getDateTime();
            timeend = timeStart.plusMinutes(i.getDuration());

            for (Event j : array){
                if (j.equals(i)) continue;

                timeStart2 = j.getDateTime();
                timeend2 = timeStart2.plusMinutes(j.getDuration());

                if ( !(timeend.isBefore(timeStart2) ||  timeend2.isBefore(timeStart))){
                    System.out.println("Overlapping events: " + i +" and " + j);
                    break outer;

                }

            }


        }

    }


    public static void analisarNext(List<Event> array){
            LocalDateTime agora = LocalDateTime.now();
            LocalDateTime proxim = null;
            String event = null;

            for(Event i: array){
                if (i.getDateTime().isAfter(agora) && (proxim == null || i.getDateTime().isBefore(proxim))){
                    proxim = i.getDateTime();
                    event = i.getName();
                }
            }

            if (proxim != null){
                System.out.println("Next event: " + event + " at " + proxim);
            } else {
                System.out.println("No upcoming events");
            }



    }


}
