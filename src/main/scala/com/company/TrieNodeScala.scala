package com.company

// import scala.annotation.tailrec
// import scala.collection.mutable
// import scala.collection.immutable.Seq

class TrieNodeScala {
  /**
   * Узел префиксного дерева
   */
  // набор ссылок на следующие узлы
  private val node: Array[TrieNodeScala] = new Array[TrieNodeScala](SuggestServiceScala.ALPHABET_SIZE - 1)
  // валидность буквы: если последняя буква слова валидна, то это слово есть в словаре
  private var value: Boolean = false
  // наличие потомка
  private var next: Boolean = false

  // проверка существования узла
  def checkNode(): Boolean =
    next

  // получение ссылки на следующий узел
  def nextNode(letter: Char): TrieNodeScala =
    node(SuggestServiceScala.getLetterPos(letter))

  // получение ссылки на следующий узел по номеру позиции буквы в словаре
  def NextNode(pos: Int): TrieNodeScala =
    node(pos)

  // добавление нового узла с буквой или без неё
  def addNode(): Unit = {
    for (i <- node.indices) {
      node(i) = new TrieNodeScala
    }
    next = true
  }

  // добавление/удаление последней буквы слова в существующий узел:
  // добавление невалидной последней буквы слова в существующий узел эквивалентно удалению этого слова
  // (да, удаление получается неполным, так как память не освобождается)
  def addLetter(letter: Char, value: Boolean): Unit = {
    node(SuggestServiceScala.getLetterPos(letter)).value = value
  }

  // проверка наличия буквы в узле или наличия буквы в потомке
  def checkLetter(letter: Char, value: Boolean): Boolean =
    checkLetterPos(SuggestServiceScala.getLetterPos(letter), value)

  // проверка наличия буквы в узле или наличия буквы в потомке по её позиции в словаре
  def checkLetterPos(pos: Int, value: Boolean): Boolean =
    if (value) node(pos).value else node(pos).next

  // добавления списка букв как новое слово в список
  def addLetters(lettersPos: List[Int], names: List[String]): List[String] = {
    var letters = List.empty[Char]
    for (pos <- lettersPos) {
      letters = SuggestServiceScala.getLetter(pos) :: letters
    }
    letters.reverse.toString() :: names
  }

  // рекурсивный обход узлов дерева с накоплением не более number имён
  // @tailrec
  /*def findName(number: Int,
               lettersPos: List[Int],
               names: List[String],
               stack: List[TrieNodeScala] = List(),
               pos: Int = 0): List[String] = {
    if (checkNode() && (names.length < number)) {
      if (checkLetterPos(pos, true) && (names.length < number)) {

      }
      else if (checkLetterPos(pos, false) && (names.length < number)) {

      }
    }
    else
      names
  }*/
}
