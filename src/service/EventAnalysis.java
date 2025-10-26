package service;

import Domain.Event;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

public class EventAnalysis {
    private HashSet<Event> array;
    public Event nextEvent;

    public EventAnalysis(HashSet<Event> array){
        this.array = array;
    }


    public static void analisarDuration(Event eventTest1, Event eventTest2) {
        //Intervalo entre dois eventos
        LocalDateTime event1 = eventTest1.getDateTime();
        LocalDateTime event2 = eventTest2.getDateTime();
        Duration duration = Duration.between(event1, event2);

        System.out.println("Time between events: "+ eventTest1+ " "+duration.toHours()+" horas "
                +"e "+ eventTest2+" " + duration.toMinutes()+" minutos");

    }


    public static String analisarOverlap( List<Event> array) {
        //Overlap
        LocalDateTime timeStart;
        LocalDateTime timeend;
        LocalDateTime timeStart2;
        LocalDateTime timeend2;

        String message = null;

        outer:for(Event i : array){
            timeStart = i.getDateTime();
            timeend = timeStart.plusMinutes(i.getDuration());

            for (Event j : array){
                if (j.equals(i)) continue;

                timeStart2 = j.getDateTime();
                timeend2 = timeStart2.plusMinutes(j.getDuration());

                if ( !(timeend.isBefore(timeStart2) ||  timeend2.isBefore(timeStart))){
                    message = "Overlapping events: " + i +" and " + j;
                    break outer;

                }

            }

        }

        if (message == null){
            return "no overlapping events";
        }

        return message;

    }


    public void analisarNext(){
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        LocalDateTime proxim = null;
        String event = null;

        for(Event i: array){
            if (i.getDateTime().isAfter(agora) && (proxim == null || i.getDateTime().isBefore(proxim))){
                proxim = i.getDateTime();
                event = i.getName();
                this.nextEvent = i;

            }
        }

        if (proxim != null){
            System.out.println("Next event: " + event + " at " + proxim.format(formatter));

        } else {
            System.out.println("No upcoming events");
        }

    }


}
