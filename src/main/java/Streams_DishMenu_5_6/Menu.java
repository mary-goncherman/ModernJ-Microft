package Streams_DishMenu_5_6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.partitioningBy;

public class Menu {

    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    static Map<String, List<String>> dishTags = new HashMap<>();

    private static void start() {
        dishTags.put("pork", Arrays.asList("greasy", "salty"));
        dishTags.put("beef", Arrays.asList("salty", "roasted"));
        dishTags.put("chicken", Arrays.asList("fried", "crisp"));
        dishTags.put("french fries", Arrays.asList("greasy", "fried"));
        dishTags.put("rice", Arrays.asList("light", "natural"));
        dishTags.put("season fruit", Arrays.asList("fresh", "natural"));
        dishTags.put("pizza", Arrays.asList("tasty", "salty"));
        dishTags.put("prawns", Arrays.asList("tasty", "roasted"));
        dishTags.put("salmon", Arrays.asList("delicious", "fresh"));
    }

    // Глава 6 --------------------------------------------------------------------------

    public static void mainn1(String[] args) {
        long howManyDishes = menu.stream().collect(Collectors.counting());
        System.out.println(howManyDishes); //9

        // мин и макс
        Comparator<Dish> dishCaloriesComporator = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish =
                menu.stream().collect(Collectors.maxBy(dishCaloriesComporator));
        System.out.println(mostCalorieDish); //pork

        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories); //4200

        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(avgCalories); //466,666 - потому и тип double!!

        IntSummaryStatistics menuStatistic =
                menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistic);

    }

    public static void mainn2(String[] args) {
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(shortMenu);
    }

    public static void mainn3(String[] args) {
        int totalCalories = menu.stream().collect(
                Collectors.reducing(0, Dish::getCalories, (i,j) -> i + j));
        System.out.println(totalCalories);

        Optional<Dish> mostCalorieDish = menu.stream().collect(
                Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(mostCalorieDish);

        int totalCalories2 = menu.stream().collect(
                Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCalories2);
    }

    public enum CaloricLevel{DIET, NORMAL, FAT}

    public static void mainn4(String[] args) {

        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
                menu.stream().collect(
                        Collectors.groupingBy(
                                dish -> {
                                    if (dish.getCalories() <=400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }));
        System.out.println(dishesByCaloricLevel);
    }

    public static void mainn5(String[] args) {

        Map<Dish.Type, List<Dish>> caloricDishesByType =
                menu.stream().collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                filtering(dish -> dish.getCalories() >500, toList())));
        System.out.println(caloricDishesByType);

        Map<Dish.Type, List<String>> dishNamesByType =
                menu.stream().collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                mapping(Dish::getName, toList())));
        System.out.println(dishNamesByType);
    }

    public static void mainn6(String[] args) {
        start();
        Map<Dish.Type, Set<String>> dishNamesByType =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,
              flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
        System.out.println(dishNamesByType);
    }

    public static void mainn7(String[] args) {

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,
                                groupingBy(dish -> {
                                    if (dish.getCalories() <=400) return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                })));
        System.out.println(dishesByTypeCaloricLevel);
    }

    public static void mainn8(String[] args) {

        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, counting()));

        System.out.println(typesCount);

        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,
                                collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));

        System.out.println(mostCaloricByType);
    }

    public static void mainn9(String[] args) {

        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(
                        Collectors.groupingBy(Dish::getType,
                                summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT; },
                                toCollection(HashSet::new))));
        System.out.println(caloricLevelsByType);

    }

    public static void mainn10(String[] args) {

        Map<Boolean, List<Dish>> partitionMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionMenu);

        List<Dish> vegetarianDishes = partitionMenu.get(true);
        System.out.println(vegetarianDishes);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegiterianDishesByType =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                groupingBy(Dish::getType)));
        System.out.println(vegiterianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian);
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("Mary");
        list1.add("Tom");
        list1.add("Bill");

        List<String> list2 = new ArrayList<>();
        list2.add("Tom");
        list2.add("Bill");

        Map<Boolean, List<String>> res = list1.stream().collect(partitioningBy(item -> list2.contains(item)));
        System.out.println(res);
    }

    public static void mainn11(String[] args) {

        Map<Boolean, Map<Boolean, List<Dish>>> listTest = menu.stream().collect(partitioningBy(
                Dish::isVegetarian,
                partitioningBy(dish -> dish.getCalories() > 500)));
        System.out.println(listTest);

        Map<Boolean, Long> intTest = menu.stream().collect(partitioningBy(Dish::isVegetarian,
                counting()));
        System.out.println(intTest);
    }

    public static void mainn12(String[] args) {
        List<Dish> dishes = menu.stream().collect(new ToListCollector<Dish>());
        dishes.forEach(System.out::println);

        List<Dish> dishes2 = menu.stream().collect(
                ArrayList::new,
                List::add,
                List::addAll);
        dishes2.forEach(System.out::println);
    }

    public static void main133(String[] args) {
        Map<Boolean, List<Integer>> result = partitionPrimesWithCustomCollector(100);
        result.get(true).forEach(System.out::println);
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
            .collect(new PrimeNumbersCollector());
    }

    // Глава 5 --------------------------------------------------------------------------

    public static void main2(String[] args){

        List<String> threeHighCaloricDishNames = menu.stream().filter(dish -> dish.getCalories() > 300)
                .map((Dish::getName))
                .limit(3)// усечение потока данных
                .collect(toList());

        System.out.println(threeHighCaloricDishNames);

        List<String> onlyVegiterian = menu.stream().filter(dish -> dish.isVegetarian())
                .map(Dish::getName).collect(toList());

        System.out.println(onlyVegiterian);

        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2) // пропуск элементов источника начинач с первого/ если в источнике меньше элементов вернет пустой список
                .collect(toList());


        //menu.stream().forEach(System.out::println);

    }

    public static void main4(String[] args){

        List<Integer> numbers = Arrays.asList(1, 6, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(integer -> integer % 2 == 0).sorted()
                .distinct()
                .forEach(System.out::println);

    }

    public static void main5(String[] args) {
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream().map(String::length)
                .collect(toList());

        wordLengths.stream().forEach(System.out::println);
    }

    public static void main6(String[] args){

        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)// отбираем, пока выполняется условие
                .collect(toList());

        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)// пропускаем, пока выполняется условие
                .collect(toList());

        slicedMenu1.stream().forEach(System.out::println);
        System.out.println("-----------------------------");
        slicedMenu2.stream().forEach(System.out::println);






    }

    // отображение mapping
    public static void main7(String[] args) {
        List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());
        List<Integer> dishNamesLength = menu.stream().map(Dish::getName).map(String::length).collect(toList());
        System.out.println(dishNames);
        System.out.println(dishNamesLength);
    }

    public static void main8(String[] args) {
        String[] words = {"Goodbye", "World"};
        //Stream<String> streamOfWords = Arrays.stream(words);
        List<String> uniqueCharacters =
                Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(uniqueCharacters);
    }

    public static void main9(String[] args) {
       Integer[] numbers = {1, 2, 3, 4, 5};
       List<Integer> superNumbers = Arrays.stream(numbers)
               .map(number -> number*number)
               .collect(toList());
        System.out.println(superNumbers);
    }

    public static void main10(String[] args) {
        boolean vegan = menu.stream().anyMatch(Dish::isVegetarian);
        boolean allVegan = menu.stream().allMatch(Dish::isVegetarian);
        boolean noVegan = menu.stream().noneMatch(Dish::isVegetarian);
        System.out.println(vegan);
        System.out.println(allVegan);
        System.out.println(noVegan);
    }

    public static void main11(String[] args) {
        Optional<Dish> dish =  menu.stream().filter(Dish::isVegetarian).findAny();// findFirst()
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(dish1 -> System.out.println(dish1.getName()));
        System.out.println(dish);
        // при параллельных потоках findAny сработает быстрее, если не важен элемент
    }

    public static void main12 (String[] args) {
        Integer[] numbers = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(numbers).reduce(0, (a,b) -> a + b);
        int sumFashion = Arrays.stream(numbers).reduce(0, Integer::sum);
        int prod = Arrays.stream(numbers).reduce(1, (a,b) -> a * b);
        Optional<Integer> min = Arrays.stream(numbers).reduce(Integer::min);// если не задать начальное значение, то вернет тип Опшанал
        Optional<Integer> max = Arrays.stream(numbers).reduce(Integer::max);
        System.out.println(sum);
        System.out.println(sumFashion);
        System.out.println(prod);
        System.out.println(min.get());
        System.out.println(max.get());
    }

    // паттерн "Отображение свертка" подсчет количества элементов в потоке
    public static void main13 (String[] args) {
        int result = menu.stream().map(dish -> 1).reduce(0, (a,b) -> a+b);
        System.out.println(result);
        long count = menu.stream().count();
        System.out.println(count);
    }

    public static void main14 (String[] args) {
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);// удобнг тем, что поумолчанию вернет 0;
        Stream<Integer> stream = intStream.boxed();// преобразует числовой поток в объект Stream

        OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();
        int maxCalRes = maxCalories.orElse(1);
        System.out.println(maxCalRes);
    }

    //работа с числовыми диапазонами
    public static void main15 (String[] args) {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(value -> value % 2 == 0);
        System.out.println(evenNumbers.count());

        IntStream evenNumbers2 = IntStream.range(1, 100)
                .filter(value -> value % 2 == 0);
        System.out.println(evenNumbers2.count());
    }

    // Пифагоровы тройки
    public static void main16 (String[] args) {

        Stream<int[]> pyrhagorianTriples =
                IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                            .filter(b -> Math.sqrt(a*a + b*b)%1 == 0)
                            .mapToObj(b ->
                                    new int[]{a,b,(int)Math.sqrt(a*a + b*b)})
                );
        pyrhagorianTriples.limit(6)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }

    public static void main17 (String[] args) {
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> streamEmpty = Stream.empty();
    }

    public static void main18 (String[] args) {

        String homeValue = System.getProperty("home");
        System.out.println("----" + homeValue);
        Stream<String> homeValueStream = Stream.ofNullable(homeValue);
                //(homeValue == null) ? Stream.empty() : Stream.of(homeValue);
        homeValueStream.forEach(System.out::println);

        Stream<String> values = Stream.of("home", "user", "config")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));
        values.forEach(System.out::println);

        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

    public static void main19(String[] args) {

        long uniqueWords = 0;
        try (Stream<String> lines =
                        Files.lines(Paths.get("D:\\Java study\\example.txt"), Charset.defaultCharset())) {

            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println(uniqueWords);
        } catch (IOException e) {
            // ignore
        }
    }

    //создание бесконечных потоков iterate/generate
    public static void main20(String[] args) {
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("Последовательност Фибоначи");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(5)
                .map(t -> t[0])
                .forEach(System.out::println);
        System.out.println("-----------------");
        IntStream.iterate(0, n -> n + 4)
                .takeWhile(n -> n < 10)
                .forEach(System.out::println);
        System.out.println("----------------");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    //числа фибоначи в бесконечном потоке и ананимном классе
    public static void main21(String[] args) {
        IntSupplier fib = new IntSupplier() {

            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
    ///----------------------------------------------------------------------------------



    //--------------------------------------------------------------------------------
    public static void forMax(String[] args) throws IOException {

        File file = new File("D:\\Java study\\example.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            String[] split = st.split("="); // тут из строки получаю массив элементов, разделенных заданным символом
            //далее, метод replaceAll заменяет все кавычки на пробелы, так я устранила кавычки
            String part1 = split[0].replaceAll("\"", "");
            String part2 = split[1].replaceAll("\"", "");
            System.out.println(part1 + " " + part2);
            anyMethod(Integer.valueOf(part1), part2);
        }
    }

    private static void anyMethod(Integer number, String text) {
        // какой-то метод который что-то делает
        System.out.println(number * number);
        System.out.println(text);
    }

}






















