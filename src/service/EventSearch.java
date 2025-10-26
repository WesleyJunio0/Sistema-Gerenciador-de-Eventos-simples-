package service;

import Domain.Event;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventSearch {
    public static void padrao(String padrao, String args) {
        //Encontrar padrão
        Pattern pattern = Pattern.compile(padrao+".*");
        Matcher matcher = pattern.matcher(args);
        if (matcher.matches()) {
            System.out.println( "Encontrado: "+ args);

        }

    }


    public static void padraoD(String padrao, Event args) {
        //Encontrar padrão
        Pattern pattern = Pattern.compile(padrao);
        Matcher matcher = pattern.matcher(args.toString());
        if (matcher.find()) {
            System.out.println( "Encontrado: "+ args);

        }


    }


    public static List<Event> padraoDArray(String padrao, HashSet<Event> eventSet) {
        //Encontrar padrão
        Pattern pattern = Pattern.compile(padrao);
        List<Event> list = new ArrayList<>();
        int cont = 0;
        for (Event i: eventSet){
            Matcher matcher = pattern.matcher(i.toString());
            if (matcher.find()) {
                list.add(i);

            } else {
                cont++;
                if (cont == eventSet.size() ){
                    System.out.println(" \"Error: Event not found\"");
                }
            }
        }

        return list;
    }
}
