import java.util.*;

class Solution {

    private static List<List<Node>> graph;

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path: paths) {
            int start = path[0];
            int end = path[1];
            int weight = path[2];

            //gate 인 경우에는 다른 곳으로만 갈 수 있다.
            if (isGate(gates, start)) {
                graph.get(start).add(new Node(end, weight));
            }
            else if (isGate(gates, end)) {
                graph.get(end).add(new Node(start, weight));
            }
            //산봉우리인 경우에는 산봉우리로만 올 수 있다.
            else if (isSummit(summits, start)) {
                graph.get(end).add(new Node(start, weight));
            }
            else if (isSummit(summits, end)) {
                graph.get(start).add(new Node(end, weight));
            }
            else {
                graph.get(start).add(new Node(end, weight));
                graph.get(end).add(new Node(start, weight));
            }
        }

        //다익스라를 사용해서 각 정상까지 도착하는데 필요한 intensity 를 구함
        return dijkstra(n, gates, summits);
    }

    public static int[] dijkstra(int n, int[] gates, int[] summits) {
        Queue<Node> queue = new LinkedList<>();
        //i 번 노드까지 가는데 필요한 min intensity
        int[] intensity = new int[n+1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        //모든 출발지점을 queue 에 추가
        for (int gate: gates) {
            queue.add(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while (!queue.isEmpty()) {
            Node nowNode = queue.poll();

            //이미 현재 노드의 weight 가 intensity 보다 커지면 해당 케이스 skip
            if (nowNode.weight > intensity[nowNode.end]) {
                continue;
            }

            //지금 노드가 방문할 수 있는 노드를 모두 탐색한다.
            for (int i = 0; i < graph.get(nowNode.end).size(); i++) {
                Node nextNode = graph.get(nowNode.end).get(i);

                //다음 노드로 가는데 필요한 피로도는, 다음 노드에 기록되어 있는 피로도와 지금 가중치의 비교
                int nextIntensity = Math.max(intensity[nowNode.end], nextNode.weight);

                //intensity 에 존재하는 값보다 현재의 피로도가 더 작다면 업데이트를 해준다.
                if (intensity[nextNode.end] > nextIntensity) {
                    intensity[nextNode.end] = nextIntensity;
                    queue.add(new Node(nextNode.end, nextIntensity));
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = Integer.MAX_VALUE - 1;
        answer[1] = Integer.MAX_VALUE - 1;

        Arrays.sort(summits);

        for (int summit: summits) {
            if (intensity[summit] < answer[1]) {
                answer[0] = summit;
                answer[1] = intensity[summit];
            }
        }

        return answer;
    }

    public static boolean isGate(int[] gates, int point) {
        for (int i = 0; i < gates.length; i++) {
            if (gates[i] == point) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSummit(int[] summits, int point) {
        for (int i = 0; i < summits.length; i++) {
            if (summits[i] == point) {
                return true;
            }
        }
        return false;
    }
}

//산의 각 지점
class Node {

    int end;
    int weight;

    public Node(int e, int w) {
        this.end = e;
        this.weight = w;
    }
}