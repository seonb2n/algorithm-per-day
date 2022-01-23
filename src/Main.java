import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb;
    static int caseNumber;
    static long[] dp;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        caseNumber = Integer.parseInt(br.readLine());

        dp = new long[101];
        numbers = new int[caseNumber];

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        dp[6] = 3;

        for (int i = 0; i < caseNumber; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < caseNumber; i++) {
            System.out.println(getNumber(numbers[i]));
        }
    }


    public static long getNumber(int i) {
        if(i <= 0) {
            return 0;
        } else {
            if(dp[i] != 0) {
                return dp[i];
            }
            dp[i] = getNumber(i - 2) + getNumber(i - 3);
            return dp[i];
        }
    }
}

