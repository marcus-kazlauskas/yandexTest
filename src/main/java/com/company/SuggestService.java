package com.company;

import java.util.*;

public class SuggestService {
    /**
     * Саджест названий компаний по подстроке
     */
    // размер алфавита
    public static final int ALPHABET_SIZE = 34;
    // словарь "буква - ранг по частоте использования"
    private static final Map<Character, Integer> letterMap = new HashMap<>(128);
    // словарь "ранг по частоте использования - буква"
    private static final Map<Integer, Character> rankMap = new HashMap<>(64);

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
        // продолжение словаря "буква - ранг по частоте использования" с заглавными буквами
        letterMap.put('О', 0);
        letterMap.put('Е', 1);
        letterMap.put('А', 2);
        letterMap.put('И', 3);
        letterMap.put('Н', 4);
        letterMap.put('Т', 5);
        letterMap.put('С', 6);
        letterMap.put('Р', 7);
        letterMap.put('В', 8);
        letterMap.put('Л', 9);
        letterMap.put('К', 10);
        letterMap.put('М', 11);
        letterMap.put('Д', 12);
        letterMap.put('П', 13);
        letterMap.put('У', 14);
        letterMap.put('Я', 15);
        letterMap.put('Ы', 16);
        letterMap.put('Ь', 17);
        letterMap.put('Г', 18);
        letterMap.put('З', 19);
        letterMap.put('Б', 20);
        letterMap.put('Ч', 21);
        letterMap.put('Й', 22);
        letterMap.put('Х', 23);
        letterMap.put('Ж', 24);
        letterMap.put('Ш', 25);
        letterMap.put('Ю', 26);
        letterMap.put('Ц', 27);
        letterMap.put('Щ', 28);
        letterMap.put('Э', 29);
        letterMap.put('Ф', 30);
        letterMap.put('Ъ', 31);
        letterMap.put('Ё', 32);
        // пробел - символ по умолчанию
        // letterMap.put(' ', 33);

        // словарь "ранг по частоте использования - буква"
        rankMap.put(0, 'о');
        rankMap.put(1, 'е');
        rankMap.put(2, 'а');
        rankMap.put(3, 'и');
        rankMap.put(4, 'н');
        rankMap.put(5, 'т');
        rankMap.put(6, 'с');
        rankMap.put(7, 'р');
        rankMap.put(8, 'в');
        rankMap.put(9, 'л');
        rankMap.put(10, 'к');
        rankMap.put(11, 'м');
        rankMap.put(12, 'д');
        rankMap.put(13, 'п');
        rankMap.put(14, 'у');
        rankMap.put(15, 'я');
        rankMap.put(16, 'ы');
        rankMap.put(17, 'ь');
        rankMap.put(18, 'г');
        rankMap.put(19, 'з');
        rankMap.put(20, 'б');
        rankMap.put(21, 'ч');
        rankMap.put(22, 'й');
        rankMap.put(23, 'х');
        rankMap.put(24, 'ж');
        rankMap.put(25, 'ш');
        rankMap.put(26, 'ю');
        rankMap.put(27, 'ц');
        rankMap.put(28, 'щ');
        rankMap.put(29, 'э');
        rankMap.put(30, 'ф');
        rankMap.put(31, 'ъ');
        rankMap.put(32, 'ё');
        // пробел - символ по умолчанию
        rankMap.put(33, ' ');
    }

    private final TrieNode root;  // корень дерева
    private TrieNode currentNode;  // узел дерева, с которым сейчас работаем

    static int getLetterPos(char letter) {
        return letterMap.getOrDefault(letter, ALPHABET_SIZE - 1);
    }

    static char getLetter(int pos) {
        return rankMap.get(pos);
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
        ArrayList<Integer> lettersPos = new ArrayList<>();
        char[] inputLetters = input.toCharArray();
        for (char c : inputLetters) {
            lettersPos.add(getLetterPos(c));
        }

        if (this.checkName(input, true)) {
            this.currentNode.addLetters(names, lettersPos);
            this.currentNode.getName(names, lettersPos, numberOfSuggest);
            return Collections.unmodifiableList(names);
        }
        else if (this.checkName(input, false) || this.checkEmpty(input)) {
            this.currentNode.getName(names, lettersPos, numberOfSuggest);
            return Collections.unmodifiableList(names);
        }
        else {
            return Collections.emptyList();
        }
    }
}
