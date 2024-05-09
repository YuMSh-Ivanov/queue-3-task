# Очередь с Javadoc'ом.

В этом задании Вам необходимо:
1. Исправить все недочёты, которые присутствовали в задании queue-2 **с модификацией**.
2. Поменять все контракты методов на Javadoc-комментарии, а также написать Javadoc ко всем остальным функциям, кроме методов.
3. Javadoc, в отличие от контрактов, должен быть написан человеко-читаемым английским текстом, а не математической записью.
4. В образовательных целях javadoc компилируется с опциями `-Werror` и `-private`, то есть всё (в т.ч. приватные сущности) документироваться должно и не должно содержать предупреждений.
5. Команда компиляции Javadoc из тестов выглядит так (для компиляции локально под Windows замените прямые слеши (`/`) на обратные (<code>&#92;</code>)):
```bash
javadoc -cp "src" -d "doc" -private -verbose -Werror -author -version src/queue/AbstractQueue.java src/queue/ArrayQueue.java src/queue/LinkedQueue.java src/queue/Queue.java
```

## Модификация.

Помимо к `filter` и `map` реализуйте метод `reduce`:
- `Object reduce(final Object identity, final BinaryOperator<Object> accumulator)`

Этот метод должен брать [бинарный оператор](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/BinaryOperator.html) на объектах и нейтральный элемент этого бинарного оператора, и делать с их помощью [свёртку](https://en.wikipedia.org/wiki/Reduction_operator).

Говоря более математическим языком:
- Вам дают бинарный оператор (назовём его символом $\star$).
- Гарантируется, что $\star$ — ассоциативный оператор, т.е. для любых $a$, $b$ и $c$ выполнено $(a\star b)\star c=a\star (b\star c)$.
- Также дают объект (назовём его $e$), являющийся нейтральным относительно $\star$, т.е. для любого $a$ выполнено $a\star e=e\star a=a$.
- Вам надо посчитать $a_0\star a_1\star\cdots\star a_{n-1}$, где $a_i$ — элементы очереди. А если очередь пуста, то вернуть $e$.

Пример: пусть очередь содержит целые числа. Тогда
- `reduce(0, (a, b) -> a + b)` должно вернуть сумму всех элементов в очереди.
- `reduce(1, (a, b) -> a * b)` должно вернуть произведение всех элементов в очереди.
- `reduce(Integer.MAX_VALUE, (a, b) -> Integer.min(a, b))` должно вернуть минимум всех элементов в очереди.
