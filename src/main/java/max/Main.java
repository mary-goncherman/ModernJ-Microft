package max;

import java.util.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) {
        Map<String, List<String>> erty = new HashMap<>();
        erty.put("Mary", Arrays.asList("qwer", "fgh", "rgmn"));
        erty.put("Mary22", Arrays.asList("qwer", "fgh", "rgmn"));
        for (Map.Entry entry:
             erty.entrySet()) {

            System.out.println(entry.getKey());
            System.out.println(entry.getValue());

        }
    }

    public static void main56(String[] args) {

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        list1.add("Tom");
        list1.add("Will");
        list1.add("Bill");
        list1.add("Lizzz");
        list1.add("Mag");

        list2.add("Will");
        list2.add("Bill");
        list2.add("Mag");
        list2.add("Liz");
        list2.add("Tom");
        list2.add("Jill");

        Set<String> set1 = new HashSet<>(list1);
        Set<String> set2 = new HashSet<>(list2);
        System.out.println(set1);
        System.out.println(set2);
        System.out.println(set1.retainAll(set2));
        System.out.println(set1);
        System.out.println(set2);



        //String a = "wewerer.erqerqewr.weqwrqe";
        //System.out.println(a.split("\\.").length);
    }

    public static void main2(String[] args) {

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        list1.add("Tom");
        list1.add("Will");
        list1.add("Bill");
        list1.add("Liz");
        list1.add("Mag");

        list2.add("Will");
        list2.add("Bill");
        list2.add("Mag");
        list2.add("Liz");
        list2.add("Tom");
        //list2.add("Jill");

        boolean areListsEquals = list1.stream()
                .collect(groupingBy(name -> name, counting()))
                .equals(
                        list2.stream()
                                .collect(groupingBy(name -> name, counting())));
        System.out.println("Списки равны? - " + areListsEquals);
    }
}
