import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 9999; i++) {
            set.add(i+1);
        }

        for (int i = 0; i < 10000; i++) {
            set.remove(getNumber(i));
        }

        for (Integer integer : set) {
            System.out.println(integer);
        }

    }

    public static int getNumber(int a) {
        int[] numbers = Arrays.stream(String.valueOf(a).split("")).mapToInt(Integer::parseInt).toArray();
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        result += a;
        return result;
    }

}
