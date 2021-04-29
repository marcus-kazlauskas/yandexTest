package com.company;

import java.util.LinkedList;
import java.util.ArrayList;

public class TrieNode {
    /**
     * Узел префиксного дерева
     */
    private final TrieNode[] node;  // набор ссылок на следующие узлы
    private boolean val;  // валидность буквы:
    // если последняя буква слова валидна, то это слово есть в словаре
    private boolean next;  // наличие потомка

    TrieNode() {
        this.node = new TrieNode[SuggestService.ALPHABET_SIZE];
        this.val = false;
        this.next = false;
    }

    // проверка существования узла
    boolean checkNode() {
        return this.next;
    }

    // получение ссылки на следующий узел
    TrieNode nextNode(char letter) {
        int pos = SuggestService.getLetterPos(letter);
        return this.nextNode(pos);
    }

    // получение ссылки на следующий узел по номеру позиции буквы в словаре
    TrieNode nextNode(int pos) {
        return this.node[pos];
    }

    // добавление нового узла с буквой или без неё
    void addNode() {
        for (int i = 0; i < this.node.length; i++) {
            this.node[i] = new TrieNode();
        }
        this.next = true;
    }

    // добавление/удаление последней буквы слова в существующий узел:
    // добавление невалидной последней буквы слова в существующий узел эквивалентно удалению этого слова
    // (да, удаление получается неполным, так как память не освобождается)
    void addLetter(char letter, boolean val) {
        int pos = SuggestService.getLetterPos(letter);
        this.node[pos].val = val;
    }

    // проверка наличия буквы в узле или наличия буквы в потомке
    boolean checkLetter(char letter, boolean val) {
        int pos = SuggestService.getLetterPos(letter);
        return this.checkLetterPos(pos, val);
    }

    // проверка наличия буквы в узле или наличия буквы в потомке по её позиции в словаре
    boolean checkLetterPos(int pos, boolean val) {
        if (val) {
            return this.node[pos].val;
        }
        else {
            return this.node[pos].next;
        }
    }

    // добавления списка букв как новое слово в список
    void addLetters(LinkedList<String> names, ArrayList<Integer> lettersPos) {
        char[] letterArray = new char[lettersPos.size()];
        for (int i = 0; i < lettersPos.size(); i++) {
            int pos = lettersPos.get(i);
            letterArray[i] = SuggestService.getLetter(pos);
        }
        String name = String.valueOf(letterArray);
        names.addLast(name);
    }

    // рекурсивный обход узлов дерева с накоплением не более number имён
    // (наверное, тут бы пригодилась Scala)
    void getName(LinkedList<String> names, ArrayList<Integer> lettersPos, Integer number) {
        if (this.checkNode() && (names.size() < number)) {
            TrieNode nextNode;

            for (int pos = 0; pos < SuggestService.ALPHABET_SIZE; pos++) {
                if (this.checkLetterPos(pos, true) && (names.size() < number)) {
                    lettersPos.add(pos);
                    this.addLetters(names, lettersPos);
                    nextNode = this.nextNode(pos);
                    nextNode.getName(names, lettersPos, number);
                    lettersPos.remove(lettersPos.size() - 1);
                }
                else if (this.checkLetterPos(pos, false) && (names.size() < number)) {
                    lettersPos.add(pos);
                    nextNode = this.nextNode(pos);
                    nextNode.getName(names, lettersPos, number);
                    lettersPos.remove(lettersPos.size() - 1);
                }
            }
        }
    }
}
