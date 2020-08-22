package parallel_work_7;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ParallelStreamsTest {

    final static String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita " +
            "mi  ritrovai in una  selva oscura" +
            " ché la  dritta via era   smarrita ";

    public static void main1(String[] args) {
        System.out.println(forkJoinSum(10000));
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args) {

        Stream<Character> stream = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        //stream.forEach(System.out::println);
        System.out.println("Найдено " + countWords(stream) + " слов");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
        System.out.println("Найдено " + countWords(parallelStream) + " слов");
    }

    //переход от последовательного потока к параллельному может привести к ошибочному результату,
    // если результат потенциально зависит от точек разбиения потока.
    // Как исправить эту проблему? Для этого необходимо гарантировать разбиение строки только в конце слов,
    // вместо произвольных точек. Потребуется реализовать сплитератор символов,
    // который разбивает строку только между двумя словами
    // и затем создает из нее параллельный поток данных.
    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(
                new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }
}
