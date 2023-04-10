import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

class Main {

    static BufferedReader br;
    static int N;
    static int M;

    static int ax1;
    static int ay1;
    static int ax2;
    static int ay2;
    static int bx1;
    static int by1;
    static int bx2;
    static int by2;

    static boolean[][] isWay;

    static final String imp = "IMPOSSIBLE";

    static int[] x_key = {-1, 0, 1, 0};
    static int[] y_key = {0, -1, 0, 1};

    /**
     * https://www.acmicpc.net/problem/5022
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        ax1 = Integer.parseInt(st.nextToken());
        ay1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        ax2 = Integer.parseInt(st.nextToken());
        ay2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        bx1 = Integer.parseInt(st.nextToken());
        by1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        bx2 = Integer.parseInt(st.nextToken());
        by2 = Integer.parseInt(st.nextToken());

        isWay = new boolean[N+1][M+1];

        // 1. A 의 최단 경로를 BFS 로 탐색, 탐색 후에 경로 마킹, 이때 경로상에 B 인 점이 있으면 안된다.
        // 2. B 의 최단 경로를 BFS 로 탐색하되, A 의 경로는 못 지나감
        // 3. 1, 2 의 과정을 B 를 먼저 탐색하는 것으로
        // 4. 1,2 의 결과와 3의 결과를 비교해서 작은 쪽으로 답 제출

    }



    static void BFS() {

    }

    static boolean inArea(int nowX, int nowY) {
        return 0 <= nowX && nowX < N && 0 <= nowY && nowY < M;
    }

}
