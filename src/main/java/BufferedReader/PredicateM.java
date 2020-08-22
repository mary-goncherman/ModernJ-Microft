package BufferedReader;

import java.io.IOException;

public interface PredicateM<T> {
    String test(T t) throws IOException;
}
