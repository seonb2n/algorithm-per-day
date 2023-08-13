import java.util.*;

class Solution {

    public int solution(int n, int s, int a, int b, int[][] fares) {
        //int[i][j] 는 i 에서 j 로 가는 최소 비용
        int[][] mapCosts = dijkstra(n, fares);

        //s 에서 특정 위치로 이동한다음, 해당 위치에서 a,b 로 이동하는 경우의 최소 값을 구한다.
        //출발지에서 각각 가는 경우
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, mapCosts[s][i] + mapCosts[i][a] + mapCosts[i][b]);
        }

        return answer;
    }

    public static int[][] dijkstra(int n, int[][] graph) {
        int[][] result = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
            result[i][i] = 0;
        }

        for (int[] edge : graph) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];
            result[from][to] = cost;
            result[to][from] = cost; // 양방향 이동을 반영
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (result[i][k] != Integer.MAX_VALUE && result[k][j] != Integer.MAX_VALUE) {
                        result[i][j] = Math.min(result[i][j], result[i][k] + result[k][j]);
                    }
                }
            }
        }

        return result;
    }
}
