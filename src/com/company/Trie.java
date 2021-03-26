package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class Trie {
    /**
     * Префиксное дерево
     */
    private final TrieNode root;  // корень дерева
    private TrieNode currentNode;  // узел дерева, с которым сейчас работаем

    Trie() {
        this.root = new TrieNode();
        this.currentNode = this.root;
    }

    // добавление/удаление имени в дерево
    void addName(String name, boolean val) {
        char[] c = name.toCharArray();
        this.currentNode = this.root;

        for (int i = 0; i < c.length; i++) {
            if (!this.currentNode.checkNode()) {
                this.currentNode.addNode();
            }
            if (i == c.length - 1) {
                this.currentNode.addLetter(c[i], val);
            }
            else {
                this.currentNode = this.currentNode.nextNode(c[i]);
            }
        }
    }

    // поиск имени или его части в дереве с сохранением указателя на узел, следующий за найденным словом
    boolean checkName(String name, boolean val) {
        char[] c = name.toCharArray();
        this.currentNode = this.root;
        boolean check = false;

        for (int i = 0; i < c.length; i++) {
            if (!this.currentNode.checkNode()) {
                return false;
            }
            if (i == c.length - 1) {
                check = this.currentNode.checkLetter(c[i], val);
            }
            this.currentNode = this.currentNode.nextNode(c[i]);
        }
        return check;
    }

    // добавление в список первых number имён из дерева, имеющих общую часть input
    void getName(LinkedList<String> names, String input, Integer number) {
        ArrayList<Character> letters = new ArrayList<>();

        if (this.checkName(input, true)) {
            char[] inputLetters = input.toCharArray();

            for (char c : inputLetters) {
                letters.add(c);
            }
            this.currentNode.addLetters(names, letters);
            this.currentNode.getName(names, letters, number);
        }
        else if (this.checkName(input, false)) {
            char[] inputLetters = input.toCharArray();

            for (char c : inputLetters) {
                letters.add(c);
            }
            this.currentNode.getName(names, letters, number);
        }
    }
}
//