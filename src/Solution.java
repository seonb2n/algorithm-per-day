import java.util.*;

class Solution {
    static boolean[] isVisited;

    public int solution(int n, int[][] computers) {
        isVisited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if(!isVisited[i]) {
                answer++;
                DFS(i, computers);
            }
        }

        return answer;
    }

    public static void DFS(int nowNode, int[][] computers) {
        isVisited[nowNode] = true;

        for (int i = 0; i < computers[nowNode].length; i++) {
            if(computers[nowNode][i] == 1) {
                //아직 방문하지 않은 곳이면 BFS
                if(!isVisited[i]) {
                    DFS(i, computers);
                }
            }
        }
    }

}