package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {
    // демонстрация работы
    public static void main(String[] args) {
        LinkedList<String> companyNames = new LinkedList<>();
        companyNames.addLast("слов");
        companyNames.addLast("слова");
        companyNames.addLast("слово");
        companyNames.addLast("слово божие");
        companyNames.addLast("слово пастыря");
        companyNames.addLast("словообразование");
        companyNames.addLast("словесный");
        companyNames.addLast("словесность");
        String input = "слово";
        Integer numberOfSuggest = 3;

        SuggestService service = new SuggestService(companyNames);
        List<String> suggest = service.suggest(input, numberOfSuggest);

        for (String name : suggest /*companyNames*/) {
            System.out.println(name);
        }
    }
}
