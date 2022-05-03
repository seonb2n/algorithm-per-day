import java.util.*;

class Solution {
    static int[] winCount;
    static int[] loseCount;
    static Node[] winMap;
    static Node[] loseMap;
    static HashSet<Integer> visited;

    public static void main(String[] args) {
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        solution(5, results);
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;
        winCount = new int[n+1];
        loseCount = new int[n+1];

        winMap = new Node[n+1];
        loseMap = new Node[n+1];

        for (int i = 0; i < n + 1; i++) {
            winMap[i] = new Node(i);
            loseMap[i] = new Node(i);
        }

        for (int i = 0; i < results.length; i++) {
            int winNumber = results[i][0];
            int loseNumber = results[i][1];
            winMap[winNumber].addVisitable(loseNumber);
            loseMap[loseNumber].addVisitable(winNumber);
        }

        //각각의 정점에서 출발했을 때, 방문할 수 있는 모든 정점을 탐색해야 한다.
        for (int i = 1; i <= n; i++) {
            visited = new HashSet<>();
            winDFS(i);
            for (Integer number : visited) {
                winCount[number]++;
            }
        }

        for (int i = 1; i <= n; i++) {
            visited = new HashSet<>();
            loseDFS(i);
            for (Integer number : visited) {
                loseCount[number]++;
            }
        }

        for (int i = 1; i <= n; i++) {
            if(winCount[i] + loseCount[i] == (n-1)) {
                answer++;
            }
        }

        return answer;
    }

    static void winDFS(int nowPoint) {
        int size = winMap[nowPoint].visitable.size();
        for (int i = 0; i < size; i++) {
            int nextNode = winMap[nowPoint].visitable.get(i);
            if(!visited.contains(nextNode)) {
                visited.add(nextNode);
                winDFS(nextNode);
            }
        }
    }

    static void loseDFS(int nowPoint) {
        int size = loseMap[nowPoint].visitable.size();
        for (int i = 0; i < size; i++) {
            int nextNode = loseMap[nowPoint].visitable.get(i);
            if(!visited.contains(nextNode)) {
                visited.add(nextNode);
                loseDFS(nextNode);
            }
        }
    }

    static class Node{
        int number;
        List<Integer> visitable;

        public Node(int number) {
            this.number = number;
            visitable = new ArrayList<>();
        }

        public void addVisitable(int nodeNumber) {
            visitable.add(nodeNumber);
        }
    }
}