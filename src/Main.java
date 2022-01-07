import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        long[] numbers = Arrays.stream(word.split(" ")).mapToLong(Long::parseLong).toArray();

        long dayHeight = numbers[0];
        long nightHeight = numbers[1];
        long treeHeight = numbers[2];

        long a = treeHeight - nightHeight;
        long b= dayHeight - nightHeight;

        long num = a /b;
        if(a % b == 0) {
            System.out.println(num);
        } else {
            System.out.println(num + 1);
        }
    }
}
