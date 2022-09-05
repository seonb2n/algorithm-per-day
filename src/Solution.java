import java.util.*;

class Solution {

    static List<Node> nodeList;
    static PriorityQueue<Point> priorityQueue;
    static boolean[][] isVisited;
    static int intensity;

    public static void main(String[] args) {
        int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates = {1, 3};
        int[] summits = {5};
        solution(6, paths, gates, summits);
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};

        nodeList = new ArrayList<>();

        //n번째 노드까지 추가해준다.
        for (int i = 0; i < n + 1; i++) {
            nodeList.add(new Node());
        }

        for (int[] path : paths) {
            int nodeOne = path[0];
            int nodeTwo = path[1];
            int intensity = path[2];

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
        answer[1] = Integer.MAX_VALUE - 1;
        for (int gate : gates) {
            for (int summit : summits) {
                priorityQueue = new PriorityQueue<>();
                isVisited = new boolean[n + 1][n + 1];
                intensity = Integer.MAX_VALUE - 1;
                priorityQueue.offer(new Point(gate, 0));
                BFS(summit);
                if (answer[1] > intensity) {
                    answer[0] = summit;
                    answer[1] = intensity;
                } else if (answer[1] == intensity && answer[0] > summit) {
                    answer[0] = summit;
                }
            }
        }

        return answer;
    }

    //startPoint 에서 targetPoint 로 가는 최소 intensity 를 구함
    public static void BFS(int targetPoint) {
        while (!priorityQueue.isEmpty()) {
            Point nowPoint = priorityQueue.poll();
            Node nowNode = nodeList.get(nowPoint.nowNode);
            int nowIntensity = nowPoint.intensity;

            if (nowIntensity >= intensity) {
                return;
            }

            //지금 노드에서 갈 수 있는 위치 탐색
            for (int i = 0; i < nowNode.toNode.size(); i++) {
                Point nextPoint = nowNode.toNode.get(i);
                Node nextNode = nodeList.get(nextPoint.nowNode);
                int nextIntensity = Math.max(nowIntensity, nextPoint.intensity);

                //다음 노드가 gate 거나 다른 산의 정상이면 스킵
                if (nextNode.isGate || (nextNode.isSummit && nextPoint.nowNode != targetPoint)) {
                    continue;
                }

                //nextIntensity 가 현재까지 정상에 갈 수 있는 intensity 보다 크면 스킵
                if (nextIntensity >= intensity) {
                    continue;
                }

                //nextPoint 가 target인 경우
                if (nextPoint.nowNode == targetPoint) {
                    intensity = nextIntensity;
                }

                //아직 방문하지 않은 노드일 때
                else if (!isVisited[nowPoint.nowNode][nextPoint.nowNode]) {
                    priorityQueue.offer(new Point(nextPoint.nowNode, nextIntensity));
                    isVisited[nowPoint.nowNode][nextPoint.nowNode] = true;
                    isVisited[nextPoint.nowNode][nowPoint.nowNode] = true;
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