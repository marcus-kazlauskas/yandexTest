package com.company;

import org.junit.Test;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrieTest {
    // модель метода addName
    private void testAddName(TrieNode currentNode, String name, boolean val) {
        System.out.println(name);
        char[] c = name.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (!currentNode.checkNode()) {
                currentNode.addNode();
                assertTrue(currentNode.checkNode());
            }
            if (i == c.length - 1) {
                currentNode.addLetter(c[i], val);
                assertTrue(currentNode.checkLetter(c[i], val));
            }
            else {
                currentNode = currentNode.nextNode(c[i]);
            }
        }
    }

    // модель метода checkName
    private void testCheckName(TrieNode currentNode, String name, boolean val) {
        char[] c = name.toCharArray();
        boolean check = false;

        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i]);

            if (!currentNode.checkNode()) {
                fail();
            }
            if (i == c.length - 1) {
                check = currentNode.checkLetter(c[i], val);
            }
            currentNode = currentNode.nextNode(c[i]);
        }
        System.out.println(", " + val);
        assertTrue(check);
    }

    @Test
    public void test0() {
        System.out.println("Тест 0: проверка, что методы addName() и checkName() в принципе работают");

        // входные данные
        ArrayList<String> names = new ArrayList<>();
        // names.add("");
        names.add("с");
        names.add("сл");
        names.add("сло");
        names.add("слов");
        String fullName  = "слово";

        TrieNode root = new TrieNode();
        TrieNode currentNode = root;

        this.testAddName(currentNode, fullName, true);
        for (String name : names) {
            this.testCheckName(currentNode, name, false);
            currentNode = root;
        }
        this.testCheckName(currentNode, fullName, true);
    }

    @Test
    public void test1() {
        System.out.println("Тест 1: input == \"\", общее кол-во названий < numberOfSuggest");

        // входные данные
        LinkedList<String> inputNames = new LinkedList<>();
        inputNames.addLast("слова");
        inputNames.addLast("слово");
        inputNames.addLast("словообразование");
        inputNames.addLast("словесность");
        String input = "";
        int numberOfSuggest = 5;
        // System.out.println("input = " + input);
        System.out.println("numberOfSuggest = " + numberOfSuggest);

        SuggestService service = new SuggestService(inputNames);
        List<String> names = service.suggest(input, numberOfSuggest);
        for (String name : names) {
            System.out.println(name);
        }
        assertEquals(inputNames.size(), names.size());
    }

    @Test
    public void test2() {
        System.out.println("Тест 2: каждое название подходит под input, их > numberOfSuggest, сам input отсутствует");

        // входные данные
        LinkedList<String> inputNames = new LinkedList<>();
        inputNames.addLast("слова");
        inputNames.addLast("слово");
        inputNames.addLast("словообразование");
        inputNames.addLast("словесность");
        String input = "слов";
        int numberOfSuggest = 3;
        System.out.println("input = " + input);
        System.out.println("numberOfSuggest = " + numberOfSuggest);

        SuggestService service = new SuggestService(inputNames);
        List<String> names = service.suggest(input, numberOfSuggest);
        for (String name : names) {
            System.out.println(name);
            assertNotEquals(input, name);
        }
        assertEquals(numberOfSuggest, names.size());
    }

    @Test
    public void test3() {
        System.out.println("Тест 3: кол-во названий, подходящих под input, > numberOfSuggest, сам input присутствует");

        // входные данные
        LinkedList<String> inputNames = new LinkedList<>();
        inputNames.addLast("слова");
        inputNames.addLast("словО");
        inputNames.addLast("словообразование");
        inputNames.addLast("слово божие");
        inputNames.addLast("слово пастыря");
        inputNames.addLast("словесность");
        String input = "слово";
        int numberOfSuggest = 3;
        System.out.println("input = " + input);
        System.out.println("numberOfSuggest = " + numberOfSuggest);

        SuggestService service = new SuggestService(inputNames);
        int inputFound = 0;
        List<String> names = service.suggest(input, numberOfSuggest);
        for (String name : names) {
            System.out.println(name);
            if (name.equals(input)) {
                inputFound += 1;
            }
        }
        assertEquals(1, inputFound);
        assertEquals(numberOfSuggest, names.size());
    }

    @Test
    public void test4() {
        System.out.println("Тест 4: в названиях и input встречаются заглавные буквы и посторонние символы");

        // входные данные
        LinkedList<String> inputNames = new LinkedList<>();
        inputNames.addLast("СЛОВА");
        inputNames.addLast("слОво");
        inputNames.addLast("словообразовАние");
        inputNames.addLast("слово-Божие");
        inputNames.addLast("слово/пастыря");
        inputNames.addLast("словесность");
        String input = "Слово";
        int numberOfSuggest = 5;
        System.out.println("input = " + input);
        System.out.println("numberOfSuggest = " + numberOfSuggest);

        SuggestService service = new SuggestService(inputNames);
        int inputFound = 0;
        List<String> names = service.suggest(input, numberOfSuggest);
        for (String name : names) {
            System.out.println(name);
            if (name.equals(input.toLowerCase())) {
                inputFound += 1;
            }
        }
        assertEquals(1, inputFound);
        assertTrue(numberOfSuggest > names.size());
    }

    @Test
    public void test5() {
        System.out.println("Тест 5: ни одно из названий не подходит под input");

        // входные данные
        LinkedList<String> inputNames = new LinkedList<>();
        inputNames.addLast("слова");
        inputNames.addLast("слово");
        inputNames.addLast("словообразование");
        inputNames.addLast("словесность");
        String input = "а";
        int numberOfSuggest = 5;
        System.out.println("input = " + input);
        System.out.println("numberOfSuggest = " + numberOfSuggest);

        SuggestService service = new SuggestService(inputNames);
        List<String> names = service.suggest(input, numberOfSuggest);
        for (String name : names) {
            System.out.println(name);
        }
        assertEquals(0, names.size());
    }
}
