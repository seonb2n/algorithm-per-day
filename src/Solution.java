import java.util.*;

class Solution {

    static int mapSize;
    static Queue<Node> queue;
    static int[] y_moves = {-1, 0, 1, 0};
    static int[] x_moves = {0, -1, 0, 1};
    static int[][] groupLand;

    static HashMap<Node, Integer> minValueMap = new LinkedHashMap<>();
    static ArrayList<Edge>[] edgeList;
    static boolean[] visited;
    static int costs = 0;

    public static void main(String[] args) {
        int[][] land = {{10, 11, 10, 11}, {2,21,20,10},{1,20,21,11},{2,1,2,1}};
        solution(land, 1);
    }

    public static int solution(int[][] land, int height) {
        mapSize = land.length;
        groupLand = new int[mapSize][mapSize];
        int answer = 0;
        //BFS
        //탐색한 지점을 queue 에 추가하고, 새로운 지점을 탐색해나가며 같은 색으로 물들인다.
        //총 색깔의 갯수를 찾으면 된다.
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (groupLand[i][j] == 0) {
                    answer++;
                    BFS(land, i, j, answer, height);
                }
            }
        }

        //그룹간에 사다리를 놓을 때 비용이 최소가 되는 지점을 찾아야 한다.
        //프림 알고리즘을 사용해 찾아보겠다.
        //완전 탐색을 통해 그룹 간의 가중치를 구한다.
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                int nowGroup = groupLand[i][j];
                for (int k = 0; k < 4; k++) {
                    int nextY = i + y_moves[k];
                    int nextX = j + x_moves[k];

                    if(inArea(nextY, nextX) && nowGroup != groupLand[nextY][nextX]) {
                        int start = Math.min(nowGroup, groupLand[nextY][nextX]);
                        int end = Math.max(nowGroup, groupLand[nextY][nextX]);

                        Node key = new Node(start, end);
                        int value = Math.abs(land[i][j] - land[nextY][nextX]);
                        if(!minValueMap.containsKey(key)) {
                            minValueMap.put(key, value);
                        }
                        else {
                            minValueMap.replace(key, Math.min(minValueMap.get(key), value));
                        }
                    }
                }
            }
        }

        visited = new boolean[answer+1];
        edgeList = new ArrayList[answer+1];
        for (int i = 1; i < answer+1; i++) {
            edgeList[i] = new ArrayList<Edge>();
        }

        for(Node n : minValueMap.keySet()) {
            int start = n.y;
            int end = n.x;
            int cost = minValueMap.get(n);
            edgeList[start].add(new Edge(start, end, cost));
            edgeList[end].add(new Edge(end, start, cost));
        }

        mst_prim(1);

        return costs;
    }

    public static void mst_prim(int p){
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();     // 비용을 오름차순으로 정렬하는 Queue
        Queue<Integer> q = new LinkedList<>();

        // 시작노드
        q.offer(p);

        while(!q.isEmpty()){
            int node = q.poll();
            visited[node] = true;

            // node에 연결된 정점들에 대한 정보 중에서
            // 방문하지 않은 Node를 pq에 담는다.
            for(Edge n : edgeList[node]){
                // 종료노드
                if(!visited[n.end]){
                    pq.add(n);
                }
            }

            // pq에 담긴 정보들을 순차적으로 mst에 담는다.
            while(!pq.isEmpty()){
                Edge e = pq.poll();

                if(!visited[e.end]){
                    q.add(e.end);
                    visited[e.end] = true;
                    costs += e.cost;
                    break;
                }
            }
        }
    }

    public static class Edge implements Comparable<Edge> {
        int start;
        int end; //진입하는 정점
        int cost; //가중치

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost <= o.cost ? -1 : 1;
        }
    }

    private static void BFS(int[][] land, int nowY, int nowX, int answer, int height) {
        queue = new LinkedList<>();
        queue.add(new Node(nowY, nowX));
        groupLand[nowY][nowX] = answer;
        while (!queue.isEmpty()) {
            Node nowNode = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nextY = nowNode.y + y_moves[k];
                int nextX = nowNode.x + x_moves[k];
                if(inArea(nextY, nextX) && groupLand[nextY][nextX] == 0 && Math.abs(land[nextY][nextX] - land[nowNode.y][nowNode.x]) <= height) {
                    groupLand[nextY][nextX] = answer;
                    queue.add(new Node(nextY, nextX));
                }
            }
        }

    }

    static boolean inArea(int y, int x) {
        return 0 <= y && y < mapSize && 0 <= x && x < mapSize;
    }

    static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Node) {
                Node n = (Node) obj;
                return this.y == n.y && this.x == n.x;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}