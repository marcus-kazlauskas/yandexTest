package com.company

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.Seq

class TrieNodeScala {
  /**
   * Узел префиксного дерева
   */
  private val node: Array[TrieNodeScala] = new Array[TrieNodeScala](SuggestServiceScala.ALPHABET_SIZE - 1)
  private var value: Boolean = false
  private var next: Boolean = false
  // проверка существования узла
  def checkNode(): Boolean = next

  // получение ссылки на следующий узел
  def nextNode(letter: Char): TrieNodeScala = ???  /* {
    val pos = SuggestServiceScala.getLetterPos(letter)
    nextNode(pos)
  } */

  // получение ссылки на следующий узел по номеру позиции буквы в словаре
  def NextNode(pos: Int): TrieNodeScala = ???  /* node(pos) */

  // добавление нового узла с буквой или без неё
  def addNode(): Unit = ???

  // добавление/удаление последней буквы слова в существующий узел:
  // добавление невалидной последней буквы слова в существующий узел эквивалентно удалению этого слова
  // (да, удаление получается неполным, так как память не освобождается)
  def addLetter(letter: Char, value: Boolean): Unit /* Nothing */ = ???

  // проверка наличия буквы в узле или наличия буквы в потомке
  def checkLetter(letter: Char, value: Boolean): Boolean = ???

  // проверка наличия буквы в узле или наличия буквы в потомке по её позиции в словаре
  def checkLetter(pos: Int, value: Boolean): Boolean = ???

  // добавления списка букв как новое слово в список
  def addLetters(names: mutable.Seq[String], lettersPos: mutable.Seq[String]): Unit = ???

  // рекурсивный обход узлов дерева с накоплением не более number имён
  // @tailrec
  def findName(names: mutable.Seq[String], lettersPos: mutable.Seq[String], number: Int): Unit = ???
}
