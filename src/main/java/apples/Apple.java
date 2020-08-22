package apples;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class Apple implements Comparable{
    private Color color;
    private int weight;
    private String country;

    public Apple(int weight, Color color, String country) {
      this.weight = weight;
      this.color = color;
      this.country = country;
    }

    public Apple(Color color, int weight) {
        this.weight = weight;
        this.color = color;
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public static void main (String[] args) {

        ArrayList<Apple> apples = new ArrayList<Apple>();
        apples.add(new Apple(230, Color.GREEN, "RF"));
        apples.add(new Apple(100, Color.YELLOW, "USA"));
        apples.add(new Apple(200, Color.GREEN, "USA"));
        apples.add(new Apple(88, Color.RED, "RF"));
        apples.add(new Apple(20, Color.BLACK, "France"));

        List<Apple> redApples;
        redApples = filter(apples, (Apple apple) -> Color.RED.equals(apple.getColor()));
        for (Apple ap : redApples) {
            System.out.println(ap.getWeight());
        }
//
//        List<Apple> anyApples = filter(apples, (Apple apple) -> apple.getWeight() >= 100 && Color.GREEN.equals(apple.getColor()));
//        for (Apple ap : anyApples) {
//            System.out.println(ap.getWeight());
//        }

        /// простой вариант сортировки
//        apples.sort(new Comparator<Apple>() {
//            public int compare(Apple o1, Apple o2) {
//                Integer res = (Integer.valueOf(o1.getWeight()).compareTo(Integer.valueOf(o2.getWeight())));
//                return res.intValue();
//            }
//        });

        apples.sort(
                (Apple a1, Apple a2) -> Integer.valueOf(a1.getWeight()).compareTo(a2.getWeight()));

        apples.sort((a1, a2) -> Integer.valueOf(a1.getWeight()).compareTo(a2.getWeight()));

        Comparator<Apple> c = comparing((Apple a) -> a.getWeight());

        apples.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry));

        for (Apple ap : apples) {
            System.out.println(ap.getWeight());
        }


        //prettyPrintApple(apples, new AppleFancyFormatter());
        //prettyPrintApple(apples, new AppleSimpleFormatter());

        //List<Apple> heavyApples = apples.parallelStream().filter((Apple a) -> a.getWeight() > 150)
        //        .collect(Collectors.toList());
        //System.out.println(heavyApples);

    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public Color getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
