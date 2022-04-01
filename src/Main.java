import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int N;
    static int[][] map;
    static int[][] dp;
    static final int INF = Integer.MAX_VALUE / 2;
    static int MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        //비트마스킹을 통해서 방문한 노드를 판별할 것이기 때문에, N 만큼의 자리를 가진 2진수를 만들어준다.
        MAX_VALUE = 1 << N;
        //해당 노드(i)에서 노드 방문 상태(j)일 때, 원점으로 돌아가는 최소 비용의 기록
        dp = new int[N][MAX_VALUE];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], INF);
        }

        //비트마스크로 방문한 정점을 기록한다.
        System.out.println(TSP(0, 1));
    }

    public static int TSP(int nowNode, int visited) {
        //모든 지점을 다 방문한 경우
        if(visited == MAX_VALUE-1) {
            //지금 노드에서 시작 지점(0)까지 가는 경로가 없다면
            if(map[nowNode][0] == 0) {
                return INF;
            }
            //지금 노드에서 0까지 가는 값을 반환한다.
            return map[nowNode][0];
        }

        //이미 지금 점에서 원점으로 돌아가는 비용이 계산되어 있다면 해당 값을 반환
        if(dp[nowNode][visited] != INF) {
            return dp[nowNode][visited];
        }

        //visited 로부터, 방문하지 않은 지점들을 찾아서 방문해줘야 한다.
        for (int i = 0; i < N; i++) {
            //각 지점을 비트 연산자로 변환시켜야 한다.
            int point = 1<<i;

            //visited 와 비교해서 각 지점을 방문했는지 안했는지 따져본다.
            //visited 에 point 가 들어가 있다면 겹치기 때문에 1이 나온다.
            //즉, 이미 해당 point 를 방문한 경우이다.
            if((visited & point) != 0) {
                continue;
            }
            //지금 위치에서 탐색하고자 하는 지점까지 길이 없는 경우도 있다.
            if(map[nowNode][i] == 0) {
                continue;
            }
            //해당 지점을 아직 방문하지 않았고 길도 존재한다면 dp 를 업데이트 해준다.
            //지금 위치에서 방문하고자 하는 지점까지의 거리 + 방문하고자 하는 지점에서 목표지점까지의 값을 업데이트해준다.
            dp[nowNode][visited] = Math.min(dp[nowNode][visited], TSP(i, point | visited) + map[nowNode][i]);
        }
        //TSP 는 지금 지점에서 방문한 지점이 visited 와 같을 떄, 최솟 값을 반환한다.
        return dp[nowNode][visited];

    }
}