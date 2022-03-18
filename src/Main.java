import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int T;
    static int S;
    static int G, H;

    static ArrayList<Node>[] adjList;
    static int[] dp;
    static PriorityQueue<Node> priorityQueue;
    static ArrayList<Integer> candi;
    static final int MAX_VAL = Integer.MAX_VALUE / 2 * 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int testCaseNumber = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCaseNumber; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N + 1];
            dp = new int[N + 1];
            candi = new ArrayList<Integer>();

            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<Node>();
            }

            Arrays.fill(dp, MAX_VAL);

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
                if (dp[i] % 2 == 1) {
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
        priorityQueue = new PriorityQueue<>();

        dp[start] = 0;
        priorityQueue.add(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {

            Node here = priorityQueue.poll();

            if (dp[here.to] < here.cost)
                continue;

            for (Node next : adjList[here.to]) {
                if (dp[next.to] > dp[here.to] + next.cost) {
                    dp[next.to] = dp[here.to] + next.cost;
                    priorityQueue.add(new Node(next.to, dp[next.to]));
                }
            }
        }
    }
}
