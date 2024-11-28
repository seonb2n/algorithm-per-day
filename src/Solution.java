// https://leetcode.com/problems/maximal-square
// dp?
class Solution {
    public int maximalSquare(char[][] matrix) {
        int res = 0;
        int[][] dp = new int[matrix.length + 2][matrix[0].length + 2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i+1][j+1] = Math.min(Math.min(dp[i][j], dp[i][j + 1]), dp[i + 1][j]) + 1;
                    res = Math.max(res, dp[i + 1][j + 1]);
                }
            }
        }
        return res * res;
    }

}
