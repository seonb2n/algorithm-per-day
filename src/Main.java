import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int testCaseNumber;
    static int N;
    static int M;
    static int K;
    static int[][] dist;
    static List<Vertex>[] airLineMap;
    static final int INF = Integer.MAX_VALUE/2;

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
            airLineMap = new ArrayList[N+1];

            for (int j = 0; j < N+1; j++) {
                airLineMap[j] = new ArrayList<>();
            }

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int startPoint = Integer.parseInt(st.nextToken());
                int endPoint = Integer.parseInt(st.nextToken());
                airLineMap[startPoint].add(new Vertex(endPoint, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            dijkstra(1);

            int min = INF;

            for (int j = 1; j <= M; j++) {
                min = Math.min(min, dist[N][j]);
            }

            if(min == INF) {
                sb.append("Poor KCM");
            }
            else {
                sb.append(min);
            }
            sb.append("\n");

        }

        System.out.println(sb);
    }

    //startPoint 로부터 M 까지 도착하는 최소 소요시간을 구한다.
    private static void dijkstra(int startPoint) {
        //i번째 지점에 j 만큼의 비용으로 올 수 있는 최소의 시간 값
        dist = new int[N+1][M+1];

        for (int i = 0; i < N+1; i++) {
            Arrays.fill(dist[i], INF);
        }

        //1번 공항 초기화
        dist[startPoint][1] = 0;

        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.offer(new Vertex(startPoint, 0, 0));

        while(!queue.isEmpty()) {
            Vertex nowVertex = queue.poll();
            int nowDestination = nowVertex.destination;
            int nowCost = nowVertex.cost;
            int nowTime = nowVertex.time;

            if(nowDestination == N) {
                break;
            }

            for (Vertex vertex : airLineMap[nowDestination]) {
                int nextDestination = vertex.destination;
                int nextCost = nowCost + vertex.cost;
                int nextTime = nowTime + vertex.time;

                //비용이 M 을 초과하는 경우는 스킵
                if(nextCost > M) {
                    continue;
                }

                //이미 해당 지점에 해당 돈으로 갈 수 있는 시간이 더 작다면
                if(dist[nextDestination][nextCost] <= nextTime) {
                    continue;
                }

                //지금 비용 이상으로 갈 수 있는 지점은 모두 지금 시간을 최소로 하게 한다. (더 비싼 비용으로 더 시간 오래 걸리는 경우를 방지)
                for (int i = nextCost; i <= M; i++) {
                    if(dist[nextDestination][i] > nextTime) {
                        dist[nextDestination][i] = nextTime;
                    }
                }

                dist[nextDestination][nextCost] = nextTime;
                queue.offer(new Vertex(nextDestination, nextCost, nextTime));
            }
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
            if(this.time < o.time) {
                return -1;
            }
            else if(this.time == o.time) {
                if(this.cost < o.cost) {
                    return -1;
                }
                return 0;
            }
            return 1;
        }
    }
}