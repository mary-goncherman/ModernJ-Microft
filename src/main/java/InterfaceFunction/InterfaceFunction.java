package InterfaceFunction;

import java.util.function.Function;

public class InterfaceFunction {

    public void main() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1); // 4

        Function<Integer, Integer> h2 = f.compose(g);
        int result2 = h2.apply(1); // 3
    }

}
