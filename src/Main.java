import Domain.Event;
import service.EventUtils;
import java.time.format.DateTimeParseException;
import java.util.List;
import static service.EventUtils.*;


public class Main {


    public static void main(String[] args) {
        //"pattern [text]": Find events where name matches pattern
        //"date [yyyy-MM-dd]": Find events on specific date
        //"quit": End program
        String condicao = "date 2025-12-23";
        String[] parts = condicao.split(" ", 2);
        String command = parts[0];
        //Command
        //"between [event1] [event2]": Calculate time between two events
        //"overlap [date]": Find overlapping events on given date
        //"next": Find next upcoming event
        String condicao2 = "date 20250-12-23";
        String[] parts2 = condicao2.trim().split(" ");
        String command2 = parts2[0];

        try {
            Event[] events = { new Event("Trabalho",9,"2025-11-23 10:50"),
                    new Event("Trabalho",9,"2025-12-23 10:50"),
                    new Event("Trabalho",9,"2025-11-23 15:50")};
            EventUtils test2 = new EventUtils(events);
            test2.events();
            for(Event test: events){
                if(command.toLowerCase().equals("pattern")){
                    padrao(parts[1], test.toString());
                } else if(command.equals("date")) {
                    padraoD(parts[1], test.toString());
                }else {
                    System.out.println("Error: comando invalido");
                }

            }


            System.out.println("----------------------------------------");
            List<Event> araa = test2.padraoDArray(parts2[1]);
            if (command2.toLowerCase().equals("between")){
                analizarDuration(events[0],events[1]);

            } else if (command2.toLowerCase().equals("overlap")) {

                analisarDt(araa);

            }else if (parts2[0].equals("next")) {
                analisarNext(araa);
            }


        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format");
        }

    }
}