package com.company;

import java.util.*;

public class SuggestService {
    /**
     * Саджест названий компаний по подстроке
     */
    // размер алфавита
    public static final int ALPHABET_SIZE = 34;
    // словарь "буква - ранг по частоте использования"
    private static final Map<Character, Integer> letterMap = new HashMap<>(64);
    // словарь "ранг по частоте использования - буква"
    private static final Map<Integer, Character> rangeMap = new HashMap<>(64);

    static {
        // словарь "буква - ранг по частоте использования"
        letterMap.put('о', 0);
        letterMap.put('е', 1);
        letterMap.put('а', 2);
        letterMap.put('и', 3);
        letterMap.put('н', 4);
        letterMap.put('т', 5);
        letterMap.put('с', 6);
        letterMap.put('р', 7);
        letterMap.put('в', 8);
        letterMap.put('л', 9);
        letterMap.put('к', 10);
        letterMap.put('м', 11);
        letterMap.put('д', 12);
        letterMap.put('п', 13);
        letterMap.put('у', 14);
        letterMap.put('я', 15);
        letterMap.put('ы', 16);
        letterMap.put('ь', 17);
        letterMap.put('г', 18);
        letterMap.put('з', 19);
        letterMap.put('б', 20);
        letterMap.put('ч', 21);
        letterMap.put('й', 22);
        letterMap.put('х', 23);
        letterMap.put('ж', 24);
        letterMap.put('ш', 25);
        letterMap.put('ю', 26);
        letterMap.put('ц', 27);
        letterMap.put('щ', 28);
        letterMap.put('э', 29);
        letterMap.put('ф', 30);
        letterMap.put('ъ', 31);
        letterMap.put('ё', 32);
        letterMap.put(' ', 33);

        // словарь "ранг по частоте использования - буква"
        rangeMap.put(0, 'о');
        rangeMap.put(1, 'е');
        rangeMap.put(2, 'а');
        rangeMap.put(3, 'и');
        rangeMap.put(4, 'н');
        rangeMap.put(5, 'т');
        rangeMap.put(6, 'с');
        rangeMap.put(7, 'р');
        rangeMap.put(8, 'в');
        rangeMap.put(9, 'л');
        rangeMap.put(10, 'к');
        rangeMap.put(11, 'м');
        rangeMap.put(12, 'д');
        rangeMap.put(13, 'п');
        rangeMap.put(14, 'у');
        rangeMap.put(15, 'я');
        rangeMap.put(16, 'ы');
        rangeMap.put(17, 'ь');
        rangeMap.put(18, 'г');
        rangeMap.put(19, 'з');
        rangeMap.put(20, 'б');
        rangeMap.put(21, 'ч');
        rangeMap.put(22, 'й');
        rangeMap.put(23, 'х');
        rangeMap.put(24, 'ж');
        rangeMap.put(25, 'ш');
        rangeMap.put(26, 'ю');
        rangeMap.put(27, 'ц');
        rangeMap.put(28, 'щ');
        rangeMap.put(29, 'э');
        rangeMap.put(30, 'ф');
        rangeMap.put(31, 'ъ');
        rangeMap.put(32, 'ё');
        rangeMap.put(33, ' ');
    }

    private final TrieNode root;  // корень дерева
    private TrieNode currentNode;  // узел дерева, с которым сейчас работаем

    static int getLetterPos(char letter) {
        return letterMap.get(letter);
    }

    static char getLetter(int pos) {
        return rangeMap.get(pos);
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

    boolean checkEmpty(String name) {
        this.currentNode = this.root;
        return name.equals("");
    }

    public SuggestService(List<String> companyNames) {
        this.root = new TrieNode();
        // this.currentNode = this.root;
        for(String name : companyNames) {
            this.addName(name, true);
        }
    }

    // добавление в список первых numberOfSuggest имён из дерева, имеющих общую часть input
    public List<String> suggest(String input, Integer numberOfSuggest) {
        LinkedList<String> names = new LinkedList<>();
        ArrayList<Character> letters = new ArrayList<>();
        char[] inputLetters = input.toCharArray();
        for (char c : inputLetters) {
            letters.add(c);
        }

        if (this.checkName(input, true)) {
            this.currentNode.addLetters(names, letters);
            this.currentNode.getName(names, letters, numberOfSuggest);
            return Collections.unmodifiableList(names);
        }
        else if (this.checkName(input, false) || this.checkEmpty(input)) {
            this.currentNode.getName(names, letters, numberOfSuggest);
            return Collections.unmodifiableList(names);
        }
        else {
            return Collections.emptyList();
        }
    }
}
