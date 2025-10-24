import Domain.Event;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

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

    public static void events(Event[] event) throws DateTimeParseException {
        HashSet<String> test = new HashSet<>();

        for(int i = 0; i < event.length; i++){

            if (event[i].getName().isEmpty() || event[i].getName() == null){
                System.out.println("Error: Event name cannot be empty");
                return;
            }
            if (event[i].getDuration() <= 0){
                System.out.println("Error: Duration must be positive");
                return;
            }

            if (test.contains(event[i].toString())){
                System.out.println("Error: Event \""+ event[i].toString() +"\" already exists");
                return;

            }
            if (isdateLocal(event[i].getDateTime())){
                System.out.println("Error: Event cannot be in the past");
                return;
            }



            test.add(event[i].toString());
            System.out.println("Event added: "+event[i].toString());

        }

    }

    public static void main(String[] args) {


        String condicao = "date 2025-11-23";
        String[] parts = condicao.split(" ", 2);
        String command = parts[0];

        try {
            Event[] events = { new Event("Trabalho",9,"2025-11-23 10:50"),
                    new Event("Trabalho",9,"2025-12-23 10:50"),
                    new Event("Trabalho",9,"2025-11-23 15:50")};
            events(events);
            for (Event test: events){

                if (command.toLowerCase().equals("pattern")){
                    padrao(parts[1], test.toString());
                } else if(command.equals("date")) {
                    padraoD(parts[1], test.toString());
                }

            }

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format");
        }






    }
}