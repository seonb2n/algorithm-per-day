import java.util.Arrays;

class Solution {
    static int [][] roads;

    public static void main(String[] args) {
        int[][] edges = {{1,2,1},{3,2,1}};
        int[] users = {1,2,1};
        solution(3, edges, users, 1, 2);
    }

    public static int solution(int n, int[][] edges, int[] users, int d, int limit) {

        roads = new int[n+1][n+1];

        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int dis = edges[i][2];
            roads[from][to] = dis;
            roads[to][from] = dis;
        }

        int[] edgePersonNumber = new int[n+1];
        for (int i = 0; i < users.length; i++) {
            edgePersonNumber[i+1] += users[i];
            for (int j = 1; j < n; j++) {
                int dist = roads[i+1][j];
                if (dist != 0 && dist <= d) {
                    edgePersonNumber[j] += users[i];
                }
            }
        }

        Arrays.sort(edgePersonNumber);

        int answer = 0;
        for (int i = 0; i < 2; i++) {
            if (edgePersonNumber[i] >= limit) {
                answer += limit;

            }
            else {
                answer += edgePersonNumber[i];
            }
        }

        return answer;
    }

}