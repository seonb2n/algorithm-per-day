import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static BufferedReader br;
    static int N;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] numbers = new int[10];

        for (int i = 1; i <= N; i++) {
            String target = String.valueOf(i);
            for (int j = 0; j < target.length(); j++) {
                numbers[Integer.parseInt(String.valueOf(target.charAt(j)))]++;
            }
        }

        for (int i = 0; i < numbers.length; i++) {
            sb.append(numbers[i]);
            sb.append(" ");
        }

        System.out.println(sb);
    }
}
