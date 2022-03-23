import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int N;
    static int M;
    static Line[] lines;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new long[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        lines = new Line[M];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            lines[i] = new Line(from, to, cost);
        }

        if(bellmanford(1)) {
            System.out.println(-1);
        }
        else {
            for (int i = 2; i < N+1; i++) {
                if(dist[i] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                }
                else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

    public static boolean bellmanford(int start) {
        dist[start] = 0;

        //총 N 번 반복한다.
        for (int i = 1; i < N + 1; i++) {
            //매 반복마다 갈 수 있는 모든 간선을 확인한다.
            for (int j = 0; j < M; j++) {
                int nowNode = lines[i].from;
                int nextNode = lines[i].to;
                int cost = lines[i].cost;

                if(dist[nowNode] == Integer.MAX_VALUE) {
                    continue;
                }

                //다음 지점으로 갈 때, 현재 지점을 지나서 가는 경우가 제일 쌀 떄
                if(dist[nextNode] > (dist[nowNode] + cost)) {
                    dist[nextNode] = dist[nowNode] + cost;

                    //N 번째 까지 왔다는 것은 은수 순환에 빠졌다는 것
                    if(i == N) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static class Line {
        int from;
        int to;
        int cost;

        public Line(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}