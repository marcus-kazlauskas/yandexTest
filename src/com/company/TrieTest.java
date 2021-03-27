package com.company;

import org.junit.Test;

import java.util.LinkedList;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void testAddCheck() {
        System.out.println("Тест 1: добавление слова в дерево и проверка, что оно действительно записалось");

        // Trie trie = new Trie();
        // модель конструктора Trie
        TrieNode root = new TrieNode();
        TrieNode currentNode;

        // входные данные
        String name = "слово";
        char[] c = name.toCharArray();

        // trie.addName(name, true);
        // модель метода addName
        currentNode = root;

        for (int i = 0; i < c.length; i++) {
            if (!currentNode.checkNode()) {
                currentNode.addNode();
                assertTrue(currentNode.checkNode());
            }
            if (i == c.length - 1) {
                currentNode.addLetter(c[i], true);
                assertTrue(currentNode.checkLetter(c[i], true));
            }
            else {
                currentNode = currentNode.nextNode(c[i]);
            }
        }

        // trie.checkName(name, true);
        // модель метода checkName
        currentNode = root;
        boolean check = false;

        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
            if (!currentNode.checkNode()) {
                fail();
            }
            if (i == c.length - 1) {
                check = currentNode.checkLetter(c[i], true);
            }
            currentNode = currentNode.nextNode(c[i]);
        }
        assertTrue(check);
    }

    @Test
    public void testAddGet() {
        System.out.println("Тест 2: запись слов в дерево, затем получение списка слов, содержащихся в дереве");

        LinkedList<String> inputNames = new LinkedList<>();
        inputNames.addLast("слова");
        inputNames.addLast("слово");
        inputNames.addLast("словообразование");
        inputNames.addLast("словесность");
        LinkedList<String> names = new LinkedList<>();
        String input = "слов";
        Integer number = 3;

        // Trie trie = new Trie();
        // модель конструктора Trie
        TrieNode root = new TrieNode();
        TrieNode currentNode;

        System.out.println("записанные слова:");
        for (String name : inputNames) {
            System.out.println(name);
            // trie.addName(name, true);
            // модель метода addName: добавление названий в дерево
            char[] c = name.toCharArray();
            currentNode = root;

            for (int i = 0; i < c.length; i++) {
                if (!currentNode.checkNode()) {
                    currentNode.addNode();
                    assertTrue(currentNode.checkNode());
                }
                if (i == c.length - 1) {
                    currentNode.addLetter(c[i], true);
                    assertTrue(currentNode.checkLetter(c[i], true));
                }
                else {
                    currentNode = currentNode.nextNode(c[i]);
                }
            }
        }
        for (String name : inputNames) {
            // trie.checkName(name, true);
            // модель метода checkName: проверка, что названия действительно записались
            char[] c = name.toCharArray();
            currentNode = root;
            boolean check = false;

            for (int i = 0; i < c.length; i++) {
                if (!currentNode.checkNode()) {
                    fail();
                }
                if (i == c.length - 1) {
                    check = currentNode.checkLetter(c[i], true);
                }
                currentNode = currentNode.nextNode(c[i]);
            }
            assertTrue(check);
        }

        // trie.getName(names, input, number);
        // модель метода getName
        char[] c = input.toCharArray();
        currentNode = root;
        boolean check = false;

        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
            if (!currentNode.checkNode()) {
                fail();
            }
            if (i == c.length - 1) {
                check = currentNode.checkLetter(c[i], true) | currentNode.checkLetter(c[i], false);
            }
            currentNode = currentNode.nextNode(c[i]);
        }
        assertTrue(check);
        System.out.println(number);

        ArrayList<Character> letters = new ArrayList<>();
        char[] inputLetters = input.toCharArray();

        for (char l : inputLetters) {
            letters.add(l);
        }
        currentNode.getName(names, letters, number);
        assertEquals(3, names.size());

        for (String name : names) {
            System.out.println(name);
        }
    }
}
//