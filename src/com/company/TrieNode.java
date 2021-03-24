package com.company;

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
        this.val = true;
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

    // проверка наличия буквы в узле:
    // либо валидна текущая буква, либо за ней есть следующая
    boolean checkLetter(char letter) {
        int pos = SuggestService.getLetterPos(letter);
        return this.node[pos].val;
    }
}
