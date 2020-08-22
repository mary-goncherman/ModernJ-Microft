package Letters;

import java.util.function.Function;

public class MainLetterTest {

    static Function<String, String> addHeader = Letter::addHeader;
    static Function<String, String> addHeaderFooter = addHeader.andThen(Letter::addFooter);
    static Function<String, String> transformationPipeline =
            addHeader.andThen(Letter::checkSpelling)
            .andThen(Letter::addFooter);
    private static String text = "Mary studies labda";


    public static void main(String[] args) {

        System.out.println(text);

        System.out.println(formLetter(text, addHeader));

        System.out.println(formLetter(text, addHeaderFooter));

        System.out.println(formLetter(text, transformationPipeline));

    }

    public static String formLetter(String text, Function<String, String> f) {
        return f.apply(text);
    }
}
