package com.company;

import java.util.LinkedList;
import java.util.List;

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
        // TrieNode currentNode = this.root;
        this.currentNode = this.root;

        for (int i = 0; i < c.length - 1; i++) {
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

    // поиск имени в дереве с сохранением указателя на узел, следующий за найденным словом
    boolean checkName(String name) {
        char[] c = name.toCharArray();
        // TrieNode currentNode = this.root;
        this.currentNode = this.root;

        for (int i = 0; i < c.length - 1; i++) {
            if (!this.currentNode.checkNode()) {
                return false;
            }
            if (i == c.length - 1) {
                boolean check = this.currentNode.checkLetter(c[i]);

                if (check) {
                    this.currentNode = this.currentNode.nextNode(c[i]);
                }
                return check;
            }
            this.currentNode = this.currentNode.nextNode(c[i]);
        }
        return false;
    }

    // добавление в список первых number имён из дерева
    void getName(LinkedList<String> names, String input, Integer number) {
        LinkedList<Character> letters = new LinkedList<>();

        if (this.checkName(input)) {
            char[] inputLetters = input.toCharArray();

            for (char c : inputLetters) {
                letters.addLast(c);
            }
            this.currentNode.getName(names, letters, number);
        }
    }
}
