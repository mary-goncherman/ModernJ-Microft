package BufferedReaderAsInBook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainTest {

    public static void main(String[] args) throws IOException {

        String oneLine = processFile((BufferedReader br) -> br.readLine());
        String threeLines = processFile((BufferedReader br) -> br.readLine() + " " + br.readLine() + " " + br.readLine());

        System.out.println(oneLine);
        System.out.println(threeLines);

    }


    public static String processFile(BofferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\Java study\\Майкрофт современная Java\\data.txt"))) {

            return p.process(br);
        }
    }


}
