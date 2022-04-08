import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static int depth;
    static List<Integer>[] nodeList;
    static int[][] dp;

    public static int solution(int[][] triangle) {
        depth = triangle.length;
        nodeList = new List[depth];
        dp = new int[depth][depth];

        for (int i = 0; i < nodeList.length; i++) {
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                nodeList[i].add(triangle[i][j]);
            }
        }

        dp[0][0] = nodeList[0].get(0);

        for (int i = 1; i < depth; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = fillDp(i, j);
            }
        }

        int answer = 0;

        for (int i = 0; i < depth; i++) {
            answer = Math.max(answer, dp[depth-1][i]);
        }

        return answer;
    }

    public static int fillDp(int i, int j) {
        //위에서부터 i 번째 높이에, j 번째 위치에 있는 숫자의 비교
        if(j == 0) {
            return dp[i-1][j] + nodeList[i].get(j);
        }

        return Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + nodeList[i].get(j);
    }
}