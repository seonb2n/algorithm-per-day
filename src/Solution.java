import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    static Queue<Integer> queue = new LinkedList<>();
    static List<Integer>[] nodeList;
    static int[] dist;
    static boolean[] isVisted;
    static int max;

    public int solution(int n, int[][] edge) {
        nodeList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            nodeList[i] = new ArrayList<>();
        }

        dist = new int[n + 1];
        isVisted = new boolean[n + 1];

        for (int i = 0; i < edge.length; i++) {
            int startPoint = edge[i][0];
            int endPoint = edge[i][1];
            nodeList[startPoint].add(endPoint);
            nodeList[endPoint].add(startPoint);
        }

        //bfs
        queue.add(1);
        isVisted[1] = true;
        dist[1] = 0;
        BFS(0);

        int answer = 0;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == max) {
                answer++;
            }
        }

        return answer;
    }

    public static void BFS(int nowDepth) {
        while (!queue.isEmpty()) {
            nowDepth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int nowNode = queue.poll();
                for (int j = 0; j < nodeList[nowNode].size(); j++) {
                    int nextNode = nodeList[nowNode].get(j);
                    if (!isVisted[nextNode]) {
                        queue.add(nextNode);
                        isVisted[nextNode] = true;
                        dist[nextNode] = nowDepth;
                    }
                }
            }
        }
        max = nowDepth-1;
    }
}