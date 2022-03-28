import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int testCaseNumber;
    static int N;
    static int M;
    static int K;
    static Node[] dist;
    static List<Vertex>[] adj;
    static final int INF = 99999;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        testCaseNumber = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            adj = new ArrayList[N+1];

            for (int j = 0; j < N+1; j++) {
                adj[j] = new ArrayList<>();
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int startPoint = Integer.parseInt(st.nextToken());
                int endPoint = Integer.parseInt(st.nextToken());
                adj[startPoint].add(new Vertex(endPoint, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            djikstra(1);
            if(dist[N].time != INF) {
                sb.append(dist[N].time);
                sb.append("\n");
            } else {
                sb.append("Poor KCM");
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    //startPoint 로부터 M 까지 도착하는 최소 소요시간을 구한다.
    private static void djikstra(int startPoint) {
        dist = new Node[N+1];
        for (int i = 0; i < N+1; i++) {
            dist[i] = new Node(0, INF);
        }

        dist[startPoint].time = 0;

        boolean[] isVisited = new boolean[N+1];
        isVisited[startPoint] = true;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(startPoint);

        while(!queue.isEmpty()) {
            int nowNode = queue.poll();
            for(Vertex vertex : adj[nowNode]) {
                if(!isVisited[vertex.destination] && dist[vertex.destination].time > dist[nowNode].time + vertex.time && M >= vertex.cost + dist[nowNode].cost) {
                    dist[vertex.destination].time = dist[nowNode].time + vertex.time;
                    dist[vertex.destination].cost = dist[nowNode].cost + vertex.cost;
                    queue.offer(vertex.destination);
                }
            }
            isVisited[nowNode] = true;
        }
    }

    public static class Node {
        int cost;
        int time;

        public Node(int cost, int time) {
            this.cost = cost;
            this.time = time;
        }
    }


    public static class Vertex implements Comparable<Vertex>{
        int destination;
        int time;
        int cost;

        public Vertex(int destination, int cost, int time) {
            this.destination = destination;
            this.time = time;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.time - o.time;
        }
    }
}