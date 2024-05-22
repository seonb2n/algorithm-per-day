import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/43105?language=java
class Solution {

    static int[][] dp;

    public int solution(int[][] triangle) {
        // dp[n][m] 은 n 번째 줄의 [m] 번째 수를 포함한 최댓값이다.
        dp = new int[triangle.length][triangle[triangle.length-1].length];
        dp[0][0] = triangle[0][0];

        if (triangle.length == 1) {
            return dp[0][0];
        }

        int floorNumber = 1;
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j <= floorNumber; j++) {
                dp[i][j] = getDp(i, j, triangle[i][j], floorNumber);
            }
            floorNumber++;
        }

        // 마지막 층 중 최댓값 찾기
        int max = 0;
        for (int i = 0; i < floorNumber; i++) {
            max = Math.max(max, dp[triangle.length-1][i]);
        }

        return max;
    }

    public int getDp(int n, int m, int nowNumber, int nowFloor) {
        if (dp[n][m] != 0) {
            return dp[n][m];
        }
        else {
            if (m == 0) {
                dp[n][m] = dp[n-1][0] + nowNumber;
            }
            else if (m == nowFloor) {
                dp[n][m] = dp[n-1][nowFloor-1] + nowNumber;
            }
            else {
                dp[n][m] = Math.max(dp[n-1][m-1], dp[n-1][m]) + nowNumber;
            }
        }
        return dp[n][m];
    }
}