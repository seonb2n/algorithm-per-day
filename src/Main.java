import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static BufferedReader br;
    static int N;
    static int[][] houseCost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        houseCost = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            houseCost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //i 번째 까지 집을 칠했을 때, i 번째 집이 j 색깔일 떄 지금까지 칠하는데 든 비용의 최솟 값
        dp = new int[N + 1][3];

        //첫번째 집의 값을 고정시킨 후 과정을 반복한다.
        dp[N][0] = Integer.MAX_VALUE;
        dp[N][1] = Integer.MAX_VALUE;
        dp[N][2] = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            int firstHouseColor = houseCost[1][i];
            for (int j = 0; j < 3; j++) {
                if(j != i) {
                    dp[2][j] = houseCost[2][j] + firstHouseColor;
                }
                else {
                    dp[2][i] = 1000 * 1000 + 1;
                }
            }

            for (int j = 3; j <= N-1; j++) {
                dp[j][0] = fillDp(j, 0);
                dp[j][1] = fillDp(j, 1);
                dp[j][2] = fillDp(j, 2);
            }

            for (int j = 0; j < 3; j++) {
                if(j != i) {
                    dp[N][j] = Math.min(dp[N][j], fillDp(N, j));
                }
            }
        }

        System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
    }

    //지금 집의 색을 칠한 dp 를 채운다.
    public static int fillDp(int nowHouseIndex, int nowHouseColor) {
        int lastCost = 0;
        switch (nowHouseColor) {
            case 0:
                lastCost = Math.min(dp[nowHouseIndex - 1][1], dp[nowHouseIndex - 1][2]);
                break;
            case 1:
                lastCost = Math.min(dp[nowHouseIndex - 1][0], dp[nowHouseIndex - 1][2]);
                break;
            case 2:
                lastCost = Math.min(dp[nowHouseIndex - 1][0], dp[nowHouseIndex - 1][1]);
                break;
        }
        return lastCost + houseCost[nowHouseIndex][nowHouseColor];
    }
}