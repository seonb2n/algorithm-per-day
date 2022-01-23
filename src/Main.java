import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb;
    static int number;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        number = Integer.parseInt(br.readLine());

        dp = new long[1000001];

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 3;
        dp[6] = 2;
        dp[7] = 3;
        dp[8] = 3;
        dp[9] = 2;
        System.out.println(getNumber(number));
    }

    //주어진 값이 i 일때 횟수의 최솟값을 구하는 함수
    public static long getNumber(int i) {
        if(dp[i] != 0) {
            return dp[i];
        } else if(i <= 1) {
            return 0;
        } else {
            if(i % 2 == 0 && i % 3 == 0) {
                dp[i] = Math.min(Math.min(getNumber(i/2), getNumber(i/3)), getNumber(i-1)) + 1;
            } else if(i % 2 == 0) {
                dp[i] = Math.min(getNumber(i/2), getNumber(i-1)) + 1;
            } else if(i % 3 == 0) {
                dp[i] = Math.min(getNumber(i/3), getNumber(i-1)) + 1;
            } else {
                dp[i] = getNumber(i-1) + 1;
            }
            return dp[i];
        }
    }
}

