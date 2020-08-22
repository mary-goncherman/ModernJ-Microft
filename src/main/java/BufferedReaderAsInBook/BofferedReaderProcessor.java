package BufferedReaderAsInBook;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BofferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
