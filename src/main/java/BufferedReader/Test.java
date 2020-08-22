package BufferedReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException  {
        //BufferedReader br = new BufferedReader(new FileReader("D:\\Java study\\Майкрофт современная Java\\data.txt"));
        String result = processFile((BufferedReader br) -> br.readLine() + " " + br.readLine());
        System.out.println(result);

    }

    public static <T> String processFile(PredicateM<T> p) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\Java study\\Майкрофт современная Java\\data.txt"));
            return p.test((T) br);
    }
}
