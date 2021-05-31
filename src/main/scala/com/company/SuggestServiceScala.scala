package com.company

import scala.collection.immutable.HashMap

class SuggestServiceScala(companyNames: Seq[String]) {
  /**
   * Саджест названий компаний по подстроке
   */
  def addName(name: String, value: Boolean): Unit = ???

  def checkName(name: String, value: Boolean): Boolean = ???

  def checkEmpty(name: String): Boolean = {
    currentNode = root
    name.equals("")
  }

  private val root: TrieNodeScala = new TrieNodeScala()
  private var currentNode = root

  def suggest(input: String, numberOfSuggest: Int): Seq[String] = ???
}

object SuggestServiceScala {
  /**
   * Словари для саджеста названий
   */
  // размер алфавита
  val ALPHABET_SIZE = 34
  // словарь "ранг по частоте использования - буква"
  private val letterMap = HashMap(
    'о' -> 0,
    'е' -> 1,
    'а' -> 2,
    'и' -> 3,
    'н' -> 4,
    'т' -> 5,
    'с' -> 6,
    'р' -> 7,
    'в' -> 8,
    'л' -> 9,
    'к' -> 10,
    'м' -> 11,
    'д' -> 12,
    'п' -> 13,
    'у' -> 14,
    'я' -> 15,
    'ы' -> 16,
    'ь' -> 17,
    'г' -> 18,
    'з' -> 19,
    'б' -> 20,
    'ч' -> 21,
    'й' -> 22,
    'х' -> 23,
    'ж' -> 24,
    'ш' -> 25,
    'ю' -> 26,
    'ц' -> 27,
    'щ' -> 28,
    'э' -> 29,
    'ф' -> 30,
    'ъ' -> 31,
    'ё' -> 32,

    'О' -> 0,
    'Е' -> 1,
    'А' -> 2,
    'И' -> 3,
    'Н' -> 4,
    'Т' -> 5,
    'С' -> 6,
    'Р' -> 7,
    'В' -> 8,
    'Л' -> 9,
    'К' -> 10,
    'М' -> 11,
    'Д' -> 12,
    'П' -> 13,
    'У' -> 14,
    'Я' -> 15,
    'Ы' -> 16,
    'Ь' -> 17,
    'Г' -> 18,
    'З' -> 19,
    'Б' -> 20,
    'Ч' -> 21,
    'Й' -> 22,
    'Х' -> 23,
    'Ж' -> 24,
    'Ш' -> 25,
    'Ю' -> 26,
    'Ц' -> 27,
    'Щ' -> 28,
    'Э' -> 29,
    'Ф' -> 30,
    'Ъ' -> 31,
    'Ё' -> 32
    // пробел - символ по умолчанию
  )
  // словарь "ранг по частоте использования - буква"
  private val rankMap = HashMap(
    0 -> 'о',
    1 -> 'е',
    2 -> 'а',
    3 -> 'и',
    4 -> 'н',
    5 -> 'т',
    6 -> 'с',
    7 -> 'р',
    8 -> 'в',
    9 -> 'л',
    10 -> 'к',
    11 -> 'м',
    12 -> 'д',
    13 -> 'п',
    14 -> 'у',
    15 -> 'я',
    16 -> 'ы',
    17 -> 'ь',
    18 -> 'г',
    19 -> 'з',
    20 -> 'б',
    21 -> 'ч',
    22 -> 'й',
    23 -> 'х',
    24 -> 'ж',
    25 -> 'ш',
    26 -> 'ю',
    27 -> 'ц',
    28 -> 'щ',
    29 -> 'э',
    30 -> 'ф',
    31 -> 'ъ',
    32 -> 'ё',
    // пробел - символ по умолчанию
  )

  def getLetterPos(letter: Char): Int = letterMap.getOrElse(letter, ALPHABET_SIZE - 1)
  def getLetter(pos: Int): Char = rankMap.getOrElse(pos, ' ')
}
