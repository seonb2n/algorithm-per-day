import java.io.*;
import java.util.*;

public class Main {

    static int N; // 노드의 수(2,000)
    static int M; // 간선의 수(50,000)
    static int T; // 목적지 후보 수(100)
    static int S; // 출발지
    static int G, H; // 지나간 경로

    static ArrayList<Node>[] adjList;
    static int[] dist;
    static PriorityQueue<Node> PQ;
    static ArrayList<Integer> candi;
    static final int MAX_VAL = Integer.MAX_VALUE / 2 * 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N + 1];
            dist = new int[N + 1];
            candi = new ArrayList<Integer>();

            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<Node>();
            }

            Arrays.fill(dist, MAX_VAL);

            st = new StringTokenizer(br.readLine());

            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                if ((from == G || from == H) && (to == G || to == H)) {
                    adjList[from].add(new Node(to, cost * 2 - 1));
                    adjList[to].add(new Node(from, cost * 2 - 1));
                } else {
                    adjList[from].add(new Node(to, cost * 2));
                    adjList[to].add(new Node(from, cost * 2));
                }

            }

            for (int i = 1; i <= T; i++) {
                candi.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(candi);

            dijkstra(S);

            for (int i : candi) {
                if (dist[i] % 2 == 1) {
                    sb.append(i);
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void dijkstra(int start) {
        PQ = new PriorityQueue<>();

        dist[start] = 0;
        PQ.add(new Node(start, 0));

        while (!PQ.isEmpty()) {

            Node here = PQ.poll();

            if (dist[here.to] < here.cost)
                continue;

            for (Node next : adjList[here.to]) {
                if (dist[next.to] > dist[here.to] + next.cost) {
                    dist[next.to] = dist[here.to] + next.cost;
                    PQ.add(new Node(next.to, dist[next.to]));
                }
            }
        }
    }
}
