import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int number;
    static int[] numbers;
    static long[] dp;
    static long max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        number = Integer.parseInt(br.readLine());
        dp = new long[100001];
        numbers = new int[number + 1];
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dp[0] = numbers[0];
        max = dp[0];

        for (int i = 1; i < number; i++) {
            dp[i] = getMax(i);
            if(max < dp[i]) {
                max = dp[i];
            }
        }

        System.out.println(max);
    }

    //i 번째에 대해서 i를 포함한 최댓값을 dp[i] 에 할당한다.
    private static long getMax(int i) {
        return Math.max(numbers[i], dp[i-1] + numbers[i]);
    }
}

