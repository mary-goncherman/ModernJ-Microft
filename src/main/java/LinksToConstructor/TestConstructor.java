package LinksToConstructor;

import apples.Apple;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TestConstructor {

    public static void main (String[] args) {

        go();

    }

    private static void go() {
        List<Integer> weights = Arrays.asList(7,3,4,10);
        List<Apple> apples = mapBi(weights, Apple::new);

        for (Apple ap: apples) {
            System.out.println(ap.getColor());
        }

    }

    public static List<Apple> mapBi(List<Integer> list, BiFunction<Color, Integer, Apple> f){
        List<Apple> result = new ArrayList<>();

        for (Integer i : list) {
            result.add(f.apply(Color.RED, i));
        }

        return result;
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();

        for (Integer i : list) {
            result.add(f.apply(i));
        }

        return result;
    }
}
