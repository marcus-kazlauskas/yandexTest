package com.company;

import java.util.LinkedList;

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

        return this.node[pos];
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

    // проверка наличия буквы в узле
    boolean checkLetter(char letter) {
        int pos = SuggestService.getLetterPos(letter);

        return this.node[pos].val;
    }

    // проверка наличия буквы в узле по её позиции в словаре
    boolean checkLetterPos(int pos) {
        return this.node[pos].val;
    }

    // рекурсивный обход узлов дерева с накоплением не более number имён
    // (наверное, тут бы пригодилась Scala)
    void getName(LinkedList<String> names, LinkedList<Character> letters, Integer number) {
        if (this.checkNode()) {
            for (int pos = 0; pos < SuggestService.ALPHABET_SIZE; pos++) {
                if (this.checkLetterPos(pos) & (names.size() < number)) {
                    char letter = SuggestService.getLetter(pos);
                    TrieNode nextNode = this.nextNode(pos);

                    letters.addLast(letter);
                    nextNode.getName(names, letters, number);
                    letters.removeLast();
                }
            }
        }
        String name = letters.toString();

        names.addLast(name);
    }
}
