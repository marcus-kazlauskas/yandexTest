package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Trie {
    /**
     * Префиксное дерево
     */
    private final TrieNode root;  // корень дерева

    Trie() {
        this.root = new TrieNode();
    }

    // добавление/удаление имени в дерево
    void addName(String name, boolean val) {
        char[] c = name.toCharArray();
        TrieNode currentNode = this.root;

        for (int i = 0; i < c.length - 1; i++) {
            if (!currentNode.checkNode()) {
                currentNode.addNode();
            }
            if (i == c.length - 1) {
                currentNode.addLetter(c[i], val);
            }
            else {
                currentNode = currentNode.nextNode(c[i]);
            }
        }
    }

    // поиск имени в дереве
    boolean checkName(String name) {
        char[] c = name.toCharArray();
        TrieNode currentNode = this.root;

        for (int i = 0; i < c.length - 1; i++) {
            if (!currentNode.checkNode()) {
                return false;
            }
            if (i == c.length - 1) {
                return currentNode.checkLetter(c[i]);
            }
            currentNode = currentNode.nextNode(c[i]);
        }
        return false;
    }

    // вывод списка первых number имён из дерева
    /*List<String> getNames(int number) {
        LinkedList<String> names = new LinkedList<>();
        ArrayList<Character> letters = new ArrayList<>();
        TrieNode currentNode = this.root;

        if (currentNode.checkNode()) {
            for (int i = 0; i < SuggestService.ALPHABET_SIZE; i++) {
                char letter = SuggestService.getLetter(i);

            }
        }
        return names;
    }*/
}
