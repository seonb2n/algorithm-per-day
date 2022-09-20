import java.util.*;

class Solution {

    static List<Node> nodeList;
    static PriorityQueue<Point> priorityQueue;
    static boolean[][] isVisited;
    static int minIntensity;
    static int[] answer;

    public static void main(String[] args) {
        int[][] paths = {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
        int[] gates = {1};
        int[] summits = {2, 3, 4};
        solution(6, paths, gates, summits);
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        nodeList = new ArrayList<>();
        minIntensity = 0;

        //n번째 노드까지 추가해준다.
        for (int i = 0; i < n + 1; i++) {
            nodeList.add(new Node());
        }

        for (int[] path : paths) {
            int nodeOne = path[0];
            int nodeTwo = path[1];
            int intensity = path[2];

            //현재 경로 탐색 중 최대의 피로도를 초기값으로 가진다.
            minIntensity = Math.max(intensity, minIntensity);

            nodeList.get(nodeOne).toNode.add(new Point(nodeTwo, intensity));
            nodeList.get(nodeTwo).toNode.add(new Point(nodeOne, intensity));
        }

        for (int gateNumber : gates) {
            nodeList.get(gateNumber).isGate = true;
        }

        for (int summitNumber : summits) {
            nodeList.get(summitNumber).isSummit = true;
        }

        //BFS 탐색
        answer = new int[2];
        answer[0] = Integer.MAX_VALUE - 1;
        answer[1] = minIntensity;

        for (int gate : gates) {
            priorityQueue = new PriorityQueue<>();
            isVisited = new boolean[n + 1][n + 1];
            priorityQueue.offer(new Point(gate, 0));
            BFS();
        }

        return answer;
    }

    //각각의 gate 에서 출발해서 정상에 도착할 때마다 intensity 를 업데이트해주는 BFS
    public static void BFS() {
        while (!priorityQueue.isEmpty()) {
            Point nowPoint = priorityQueue.poll();
            Node nowNode = nodeList.get(nowPoint.nowNode);

            //만약 지금 intensity 가 answer[1] 을 넘었다면 더 이상 탐색할 필요가 없다.
            if (nowPoint.intensity > answer[1]) {
                return;
            }

            //만약 지금 위치가 산의 정상이라면 intensity 를 업데이트해준다.
            //가장 먼저 도착한 산의 정상은 가장 적은 피로도로 도착한 것이다.
            if (nowNode.isSummit) {
                if (nowPoint.intensity < answer[1]) {
                    answer[0] = nowPoint.nowNode;
                    answer[1] = nowPoint.intensity;
                } else if (nowPoint.intensity == answer[1]) {
                    if (answer[0] > nowPoint.nowNode) {
                        answer[0] = nowPoint.nowNode;
                    }
                }
            } else {
                // 아직 피로도도 answer[1] 보다 작고 산의 정상도 아니라면 다음 노드를 탐색한다.
                for (int i = 0; i < nowNode.toNode.size(); i++) {
                    Point nextPoint = nowNode.toNode.get(i);

                    // 다음 방문할 노드가 gate 인 경우는 skip
                    if (nodeList.get(nextPoint.nowNode).isGate) {
                        continue;
                    }

                    // 다음 방문할 노드가 아직 방문 안한 노드라면
                    if (!isVisited[nowPoint.nowNode][nextPoint.nowNode] && !isVisited[nextPoint.nowNode][nowPoint.nowNode]) {
                        int nextIntensity = Math.max(nowPoint.intensity, nextPoint.intensity);
                        priorityQueue.offer(new Point(nextPoint.nowNode, nextIntensity));
                        isVisited[nowPoint.nowNode][nextPoint.nowNode] = true;
                        isVisited[nextPoint.nowNode][nowPoint.nowNode] = true;
                    }
                }
            }
        }
    }
}

//현재 위치에 오기까지 얼만큼의 intensity 로 왔는지를 의미함
class Point implements Comparable<Point> {
    int nowNode;
    int intensity;

    public Point(int nowNode, int intensity) {
        this.nowNode = nowNode;
        this.intensity = intensity;
    }

    @Override
    public int compareTo(Point o) {
        return this.intensity - o.intensity;
    }
}

//산의 각 지점
class Node {

    //현재 노드에서 갈 수 있는 node 와 그 intensity
    List<Point> toNode;
    boolean isGate;
    boolean isSummit;

    public Node() {
        this.toNode = new ArrayList<>();
        isGate = false;
        isSummit = false;
    }
}