package collections_8;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class Main {

    public static void main1(String[] args) {

        List<String> friendsArrList = Arrays.asList("Tom", "Bill", "Mag");
        System.out.println(friendsArrList);

        Set<String> friendsSet = Stream.of("Tom", "Bill", "Mag")
                .collect(Collectors.toSet());
        System.out.println(friendsSet);

        List<String> friendsList = List.of("Tom", "Bill", "Mag");
        System.out.println(friendsList);

        Set<String> friendsSet2 = Set.of("Tom", "Bill", "Mag");
        System.out.println(friendsSet2);

        // не более 10 ключей-значений
        Map<String, Integer> ageOfFriends = Map.of("Tom", 30, "Bill", 34, "Mag" , 28);
        System.out.println(ageOfFriends);

        Map<String, Integer> ageOfFriendsEntry = Map.ofEntries(
                entry("Tom", 30),
                entry("Bill", 34),
                entry("Mag" , 28)
        );

        System.out.println(ageOfFriendsEntry);

        ageOfFriends.forEach((name, age) -> System.out.println(name + " is " + age));
    }

    // сортировка по entry - map
    public static void main2(String[] args) {

       Map<String, String> favouriteMovies = Map.ofEntries(
               entry("Bill", "StarWars"),
               entry("Tom", "Matrix"),
               entry("Jack", "James Bond"));

        favouriteMovies.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);

        System.out.println(favouriteMovies.getOrDefault("Max", "Matrix"));
    }

    // паттерны вычисления
    public static void main3(String[] args) throws NoSuchAlgorithmException {
        Map<String, List<String>> friendsToMovies = new HashMap<>();
        friendsToMovies.put("Bill", Arrays.asList("StarWars"));
        friendsToMovies.put("Tom", Arrays.asList("Matrix"));
        friendsToMovies.put("Jack", Arrays.asList("James Bond"));
        friendsToMovies.entrySet().stream().forEach(System.out::println);

        // коллекция должна быть изменяемой!!!
        friendsToMovies.computeIfAbsent("Max", name -> new ArrayList<>()).add("AnyFilm");
        friendsToMovies.entrySet().stream().forEach(System.out::println);

        friendsToMovies.remove("Max", "AnyFilm2");
        friendsToMovies.entrySet().stream().forEach(System.out::println);

        friendsToMovies.remove("Max", "AnyFilm");
        friendsToMovies.entrySet().stream().forEach(System.out::println);
    }

    public static void main4(String[] args) {
        Map<String, String> friendsToMovies = new HashMap<>();
        friendsToMovies.put("Bill", "StarWars");
        friendsToMovies.put("Tom", "Matrix");
        friendsToMovies.put("Jack", "James Bond");
        friendsToMovies.replaceAll((friend, movie) -> movie.toUpperCase());
        System.out.println(friendsToMovies);
    }

    public static void main5(String[] args) {
        Map<String, String> friendsToMovies = new HashMap<>();
        friendsToMovies.put("Bill", "StarWars");
        friendsToMovies.put("Tom", "Matrix");
        Map<String, String> familyToMovies = new HashMap<>();
        familyToMovies.put("Jack", "James Bond");

        Map<String, String> all = new HashMap<>(friendsToMovies);
        all.putAll(familyToMovies);
        System.out.println(all);

        Map<String, String> familyToMovies2 = new HashMap<>();
        familyToMovies2.put("Jack", "James Bond");
        familyToMovies2.put("Tom", "James Bond-skyfall");

        Map<String, String> all2 = new HashMap<>(friendsToMovies);
        familyToMovies2.forEach((k, v) -> all2.merge(k, v, (mov1, mov2) -> mov1 + " & " + mov2));
        System.out.println(all2);
    }

    // метод peek() для журналирования значений внутри потока
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2,3,4,5);
        numbers.stream()
                .peek(x -> System.out.println("from stream " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter " + x))
                .limit(3)
                .peek(x -> System.out.println("after limit " + x))
                .collect(Collectors.toList());
    }


}
