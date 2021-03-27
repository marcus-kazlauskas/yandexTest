# Тестовое задание на должность младшего Scala-разработчика

## Условие:

Требуется написать класс на Scala или Java, реализующий саджест названий 
компаний по подстроке — из списка всех доступных названий вывести 
определённое число компаний, которые начинаются с введённой строчки. 
Предполагается, что класс будет вызываться при заполнении формы на сайте или 
мобильном приложении с высоким RPS (requests per second). Шаблон для кода 
приведён в `Template.txt`.

## Решение:

Положим, что общее количество компаний `N` ничем не ограничено и поэтому 
гораздо больше остальных переменных: длины каждого из названий; длины 
введённой строчки; числа названий, что надо вывести; размера алфавита, из 
букв которого составлены названия. Если просто сохранить ссылку на список, 
то для поиска как одной подходящей компании, так и их некоторого числа, 
потребуется совершить `O(N)` операций, что не очень быстро.

Поэтому я решил построить префиксное дерево по входному списку названий. 
Тогда поиск одной компании займёт `O(1)` операций, так как для этого 
достаточно пройти в дереве по буквам искомого слова. Но если полное название 
компании не известно, то надо сначала найти поддерево названий, начинающихся 
с введённой строчки, и обойти в нём все узлы, что потребует заметно большего 
числа операций. Однако, это число зависит от всех упомянутых выше переменных, 
кроме `N`, поэтому ожидаемая сложность обхода дерева всё равно получается 
`O(1)`.

Для упрощения реализации дерева будем считать, что в каждом названии 
присутствуют только строчные буквы русского алфавита и пробелы, а введённая 
строчка непустая.

##