package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {
    // демонстрация работы
    public static void main(String[] args) {
        LinkedList<String> companyNames = new LinkedList<>();
        companyNames.addLast("слов");
        companyNames.addLast("слова");
        companyNames.addLast("СЛОВО");
        companyNames.addLast("слово Божие");
        companyNames.addLast("слово пастыря");
        companyNames.addLast("словообразование");
        companyNames.addLast("словесный");
        companyNames.addLast("словесность");
        companyNames.addLast("слово-с-дефисами");
        String input = "Слово";
        Integer numberOfSuggest = 4;

        SuggestService service = new SuggestService(companyNames);
        List<String> suggest = service.suggest(input, numberOfSuggest);

        System.out.println("Демонстрация работы саджеста\n");
        System.out.println("companyNames:");
        for (String name : companyNames) {
            System.out.println(name);
        }
        System.out.println("\ninput = " + input);
        System.out.println("numberOfSuggest = " + numberOfSuggest);
        System.out.println("\nСаджест:");
        for (String name : suggest) {
            System.out.println(name);
        }
    }
}
