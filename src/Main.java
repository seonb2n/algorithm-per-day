import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static BufferedReader br;
    static int N;
    static int K;
    static int answer;
    static int[][] dp;
    static int mod = 1_000_000_003;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        answer = 0;

        dp = new int[N+1][K+1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i <= N; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }
        Arrays.fill(dp[0], 0);

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                fillDp(i, j);
            }
        }

        answer = (fillDp(N-3, K-1) + fillDp(N-1, K)) % mod;

        System.out.println(answer);
    }

    public static int fillDp(int i, int j) {
        if(dp[i][j] != -1) {
            return dp[i][j];
        }

        if(i == j * 2 - 1) {
            dp[i][j] = 1;
            return 1;
        }

        if(i <= j * 2 - 1) {
            dp[i][j] = 0;
            return 0;
        }

        dp[i][j] = (fillDp(i-2, j-1) + fillDp(i-1, j)) % mod;

        return dp[i][j];
    }
}