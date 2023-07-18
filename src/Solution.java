import java.util.*;

class Solution {


    public static void main(String[] args) {
        int[][] puddles = {{2,2}};
        solution(4, 3, puddles);
    }

    static int[][] memo;
    static boolean[][] notWay;

    public static int solution(int m, int n, int[][] puddles) {
        memo = new int[m+1][n+1];
        notWay = new boolean[m+1][n+1];

        for (int i = 0; i < puddles.length; i++) {
            notWay[puddles[i][0]][puddles[i][1]] = true;
        }

        memo[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (!notWay[i][j]) {
                    memo[i][j] = findMemo(i, j);
                }
            }
        }

        return memo[m][n];
    }

    public static int findMemo(int x, int y) {
        if (x < 1 || y < 1) {
            return 0;
        }

        if (notWay[x][y]) {
            return 0;
        }

        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        else {
            return (findMemo(x-1, y) + findMemo(x, y-1)) % 1_000_000_007;
        }
    }

}