import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static ArrayList<Node>[] graph;
    static BufferedReader br;
    static int N;
    static int M;
    static int count;
    static int[] dist;
    static int[] route;
    static int end;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        route = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startPoint = Integer.parseInt(st.nextToken());
            int endPoint = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[startPoint].add(new Node(endPoint, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra(start);
        sb.append(dist[end]).append("\n");

        StringBuilder sb2 = new StringBuilder();

        while (route[end] != 0) {
            count += 1;
            sb2.insert(0, end);
            sb2.insert(0, " ");
            end = route[end];
        }

        sb2.insert(0, start);

        sb.append(count + 1);
        sb.append("\n");
        sb.append(sb2);
        System.out.println(sb);

    }

    private static void dijkstra(int startPoint) {
        // 최소 비용 순으로 정렬한다.
        PriorityQueue<Node> queue = new PriorityQueue<>();
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[startPoint] = 0;
        queue.offer(new Node(startPoint, 0));
        while (!queue.isEmpty()) {
            Node nowNode = queue.poll();
            int nowPoint = nowNode.next;

            // 다음 노드가 목적지인 경우엔 더 이상 탐색할 필요가 없다.
            if (nowPoint == end) {
                return;
            }

            for (Node next : graph[nowPoint]) {
                if (dist[next.next] > dist[nowPoint] + next.cost) {
                    dist[next.next] = dist[nowPoint] + next.cost;
                    route[next.next] = nowPoint;

                    queue.offer(new Node(next.next, dist[next.next]));

                }
            }
        }
    }

    public static class Node implements Comparable<Node> {
        public int next;
        public int cost;

        public Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }


}
