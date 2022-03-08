import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static long n;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        final int PISANO = 1_500_000;
        dp = new long[PISANO];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i < PISANO; i++) {
            getDp(i);
        }

        if(n >= PISANO) {
            n %= PISANO;
        }

        System.out.println(dp[(int) n]);
    }

    public static Long getDp(int n) {
        if (dp[n] == 0) {
            dp[n] = (getDp(n - 1) + getDp(n - 2)) % 1_000_000;
        }
        return dp[n];
    }
}