# Функциональная Java

Необходимо создать следующие абстрактные классы/интерфейсы:

* `Function1` — функция одного аргумента (`f(x)`)
* `Function2` — функция от двух аргументов (`f(x, y)`)
* `Predicate` — предикат для одного аргумента

Подумайте, как лучше применить наследование, чтобы выстроить из них иерархию.

---

# Функциональная Java

В рамках этиx абстрактных классов/интерфейсов нужно создать:

* `Function1.compose` — композиция
    * принимает `Function1 g`, возвращает `g(f(x))`
    
* `Function2.compose` — композиция
    * принимает `Function1 g`, возвращает `g(f(x, y))`
    
* `Function2.bind1` — bind первого аргумента
    * принимает первый аргумент, возвращает `f(_, y)`
    
* `Function2.bind2` — bind второго аргумента
    * принимает второй аргумент, возвращает `f(x, _)`
    
* `Function2.curry` — [каррирование](https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D1%80%D1%80%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5), конвертация в `Function1`

---

# Функциональная Java

* `Predicate.or`/`Predicate.and`   
    * принимают один предикат в качестве аргумента, возвращают предикат, который ведет себя, как дизъюнкция/конъюнкция текущего предиката и предиката-аргумента
    * семантика ленивая, как у `||` и `&&`
    
* `Predicate.not` принимает 0 аргументов, возвращает предикат-отрицание текущего предиката

* Константные предикаты: `Predicate.ALWAYS_TRUE`, `Predicate.ALWAYS_FALSE`
  
---

# Функциональная Java

Cоздать класс `Collections` со следующими статическими методами, оперирующими `Iterable`, `Function1`, `Function2` и `Predicate`:

* `map` — принимает `f` и `a`, применяет `f` к каждому элементу `a_i` и возвращает список `[f(a_1), ..., f(a_n)]`

* `filter` — принимает `p` и `a`, возвращает список, содержащий элементы `a_i`, на которых `p(a_i) == true`

* `takeWhile` — принимает `p` и `a`, возвращает список с началом `a` до первого элемента `a_i`, для которого `p(a_i) == false`
 
* `takeUnless` — то же, что и `takeWhile`, только для `p(a_i) == true`

* `foldr` / `foldl` — принимает функцию двух аргументов, начальное значение и коллекцию, работает [так](https://ru.wikipedia.org/wiki/%D0%A1%D0%B2%D1%91%D1%80%D1%82%D0%BA%D0%B0_%D1%81%D0%BF%D0%B8%D1%81%D0%BA%D0%B0)
  
---

# Требования

* Maven/Gradle проект

* Тесты

* Сигнатуры классов и методов должны быть максимально гибкими (наиболее общими)

Срок: <b>26.04.2018 23:59</b>

# Links

* [https://docs.oracle.com/javase/tutorial/extra/generics/index.html](https://docs.oracle.com/javase/tutorial/extra/generics/index.html)
* [https://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html](https://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html)
* [https://docs.oracle.com/javase/tutorial/extra/generics/methods.html](https://docs.oracle.com/javase/tutorial/extra/generics/methods.html)

---

# Формат сдачи

* Задание выполняется в отдельной ветке в репозитории на GitHub
* Создается pull request ветки в master этого же репозитория
    * Тема pull request'а: Java05. ДЗ 04, &lt;фамилия&gt; &lt;имя&gt;
* Посылаете письмо преподавателю с такой же темой с ссылкой на pull request (s-proshev@ya.ru, dsavvinov@gmail.com)
