package Streams_DishMenu_5_6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        //Метод supplier должен возвращать объект типа Supplier — функцию без параметров,
        // создающую при вызове экземпляр пустого накопителя для использования в процессе сбора данных.
        //return() -> new ArrayList();
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        //Метод accumulator возвращает функцию, осуществляющую операцию свертки.
        // При обходе n-го элемента потока данных эта функция применяется с двумя аргументами —
        // накопителем (результатом свертки предыдущих n – 1 элементов потока)
        // и самим n-м элементом потока. Функция возвращает void,
        // поскольку накопитель меняется «на месте», то есть она меняет его внутреннее состояние,
        // отражая тем самым результат обработки очередного элемента
        //return (list, item) -> list.add(item);
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        //Метод combiner, возвращающий используемую операцией свертки функцию,
        // определяет, каким образом объединяются накопители,
        //полученные в результате параллельной обработки частей потока данных.
        // В случае коллектора ToList реализация этого метода проста: список,
        // содержащий собранные из второй части потока элементы,
        // добавляется в конец списка, полученного при обходе первой части:
        return (list1, list2) ->
        {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        //Метод finisher должен возвращать функцию, вызываемую в конце процесса накопления,
        // по завершении прохода по потоку данных для преобразования объекта-накопителя
        // в конечный результат всей операции сбора данных.
        // Зачастую, как и в случае ToListCollector, объект-накопитель совпадает с ожидаемым конечным результатом.
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        //Последний из методов, characteristics, возвращает неизменяемый набор типа Characteristics,
        // который определяет поведение коллектора.
        // Characteristics — перечисляемый тип, содержащий три элемента.
        //UNORDERED — порядок обхода и накопления элементов потока данных не влияет на результат свертки.
        //CONCURRENT — функцию accumulator можно вызывать параллельно из нескольких потоков выполнения.
        //IDENTITY_FINISH — указывает, что возвращаемая методом finisher функция — тождественная и ее можно не вызывать.
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
