import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static final int INF = Integer.MAX_VALUE / 2;
    static int V;
    static int E;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[V+1][V+1];

        for (int i = 1; i < V+1; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int startPoint = Integer.parseInt(st.nextToken());
            int endPoint = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[startPoint][endPoint] = cost;
        }

        //플로이드 와샬 알고리즘을 사용해서 dist 를 각 지점에 대한 최솟값으로 계산한다.
        //거치는 노드
        for (int i = 1; i < V+1; i++) {
            //출발 노드
            for (int j = 1; j < V+1; j++) {
                //도착 노드
                for (int k = 1; k < V+1; k++) {
                    if(dist[j][k] > dist[j][i] + dist[i][k]) {
                        dist[j][k] = dist[j][i] + dist[i][k];
                    }
                }
            }
        }

        //순환한다는 것은 a->b, b->a 가 INF 가 아니라는 것이다.
        int answer = INF;

        for (int i = 1; i < V + 1; i++) {
            for (int j = 1; j < V+1; j++) {
                if(i != j && dist[i][j] != INF && dist[j][i] != INF) {
                    answer = Math.min(answer, dist[i][j] + dist[j][i]);
                }
            }
        }

        if(answer != INF) {
            System.out.println(answer);
        }
        else {
            System.out.println(-1);
        }

    }
}