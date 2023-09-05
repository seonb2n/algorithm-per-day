import java.util.*;

class Solution {

    static int maxNum = 0;

    static int[] x_moves = {-1, 0, 1, 0};
    static int[] y_moves = {0, 1, 0, -1};
    static int M;
    static int N;
    static int[][] dp;

    public static void main(String[] args) {
        int [][] arr = {{1}};
        longestIncreasingPath(arr);
    }

    public static int longestIncreasingPath(int[][] matrix) {
        M = matrix.length;
        N = matrix[0].length;
        dp = new int[M+1][N+1];
        for (int i = 0; i < M + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

        // dp 로 x, y 에서 갈 수 있는 최대한의 길이를 탐색한다.
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                maxNum = Math.max(getDP(i, j, matrix), maxNum);
            }
        }
        return maxNum;
    }

    static int getDP(int nowX, int nowY, int[][] matrix) {
        if (dp[nowX][nowY] != -1) {
            return dp[nowX][nowY];
        }
        // 현재 위치에서 4방향으로 갈 수 있는 값이 없으면 1을 반환한다.
        // 갈 수 있는 값이 존재하면 해당 위치 dp + 1 이 내 값이다.
        boolean canGoNext = false;
        int max = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = nowX + x_moves[i];
            int nextY = nowY + y_moves[i];
            if (isInArea(nextX, nextY) && matrix[nowX][nowY] < matrix[nextX][nextY]) {
                canGoNext = true;
                max = Math.max(getDP(nextX, nextY, matrix), max);
            }
        }
        if (!canGoNext) {
            dp[nowX][nowY] = 1;
            return 1;
        }
        else {
            dp[nowX][nowY] = max + 1;
            return dp[nowX][nowY];
        }
    }

    static boolean isInArea(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }
}