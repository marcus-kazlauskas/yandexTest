package com.company;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void testAddCheck() {
        // модель внутренностей конструктора Trie
        TrieNode root = new TrieNode();

        // входные данные
        String name = "слово";
        char[] c = name.toCharArray();

        // модель внутренностей метода addName
        TrieNode currentNode = root;

        for (int i = 0; i < c.length - 1; i++) {
            if (!currentNode.checkNode()) {
                currentNode.addNode();
                assertTrue(currentNode.checkNode());
            }
            if (i == c.length - 1) {
                currentNode.addLetter(c[i], true);
                assertTrue(currentNode.checkLetter(c[i]));
            }
            else {
                currentNode = currentNode.nextNode(c[i]);
            }
        }

        // модель внутренностей метода checkName
        currentNode = root;

        for (int i = 0; i < c.length - 1; i++) {
            System.out.println(c[i]);
            if (!currentNode.checkNode()) {
                fail();
                // return false;
            }
            if (i == c.length - 1) {
                boolean check = currentNode.checkLetter(c[i]);

                if (check) {
                    currentNode = currentNode.nextNode(c[i]);
                }
                assertTrue(check);
                // return check;
            }
            currentNode = currentNode.nextNode(c[i]);
        }
        fail();
        // return false;
    }

    @Test
    public void testAddGet() {
        Trie trie = new Trie();
        trie.addName("слова", true);
        trie.addName("слово", true);
        trie.addName("словообразование", true);
        trie.addName("словесность", true);
        LinkedList<String> names = new LinkedList<>();
        String input = "слов";
        Integer number = 3;

        trie.getName(names, input, number);
        for (String name : names) {
            System.out.println(name);
        }
    }
}