package adding_for_work;

import java.util.ArrayList;
import java.util.List;

public class SuperDistinctTest {


    public static void main(String[] args) {


        List<NewClass> foo = new ArrayList<>();
        foo.add(new NewClass("qwer", 234));
        foo.add(new NewClass("wert", 234));
        foo.add(new NewClass("erty", 234));
        foo.add(new NewClass("qweR", 234));
        foo.add(new NewClass("werT", 234));
        foo.add(new NewClass("vbgfy", 234));
        foo.add(new NewClass("cvfrf", 234));

        for (NewClass edin : foo) {

            long res = foo.stream().filter(asd -> asd.name.equalsIgnoreCase(edin.name)).count();
            if (res > 1) {

            }
        }


    }
}
