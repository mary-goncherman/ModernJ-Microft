package parallel_work_7;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
@State(Scope.Benchmark)
public class ParallelStreamBenchmark {

    private static final long N = 10_000_000L;

    @Benchmark
    public long sequentalSum() {
        return Stream.iterate(1L, i -> i + 1)
                .limit(N)
                .reduce(0L,Long::sum);
    }

    @TearDown(Level.Invocation)
    public void tearDown() {
        System.gc();
    }

//    public static void main(String[] args) {
//        // количество параллельных потоков поумолчанию
//        System.out.println(Runtime.getRuntime().availableProcessors());
//
//        // можно изменить их количество
//        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
//    }

//    public static long parallelSum(long n) {
//        return Stream.iterate(1L, i -> i + 1)
//                .limit(n)
//                .parallel()
//                .reduce(0L,Long::sum);
//    }
}
