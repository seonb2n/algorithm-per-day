import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int[] numbers = Arrays.stream(word.split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(Math.max(reverseNumber(numbers[0]), reverseNumber(numbers[1])));

    }

    private static int reverseNumber(int number) {
        int result = number / 100;
        result += number / 10 % 10 * 10;
        result += number % 10 * 100;

        return result;
    }
}
