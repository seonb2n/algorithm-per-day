import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static BufferedReader br;
    static int N;
    static int C;
    static int E;
    static int[][] map;
    static int[] x_moves = {-1, 0, 1, 0};
    static int[] y_moves = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        if(fillC() && fillE()) {
            System.out.println(1);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    //N-1, N-1 에서부터 좌하단 방향으로 맵을 채운다.
    private static boolean fillE() {
        int nowY = N-1;
        int nowX = N-1;
        for (int i = 0; i < E; i++) {
            if(inArea(nowY, nowX) && map[nowY][nowX] == 0 && !isNear(nowY, nowX)) {
                //맵을 채우고자 할 때, C랑 겹쳐서는 안된다.
                map[nowY][nowX] = 2;
            }
            else {
                return false;
            }
            //다음 위치로 nowY, nowX 조정
            //합이 N 보다 크다는 것은 대각선 넘어로 갔다는 뜻
            if(nowY + nowX <= N-2) {
                if(nowX == 0) {
                    nowX = nowY - 1;
                    nowY = 0;
                }
                else {
                    nowX += 1;
                    nowY -= 1;
                }

            } else {
                if(nowY == N-1) {
                    nowY = nowX - 1;
                    nowX = N - 1;
                }
                else {
                    nowY += 1;
                    nowX -= 1;
                }
            }

        }

        return true;
    }

    //0,0 에서부터 우상단 방향으로 맵을 채운다.
    private static boolean fillC() {
        int nowY = 0;
        int nowX = 0;
        for (int i = 0; i < C; i++) {
            if(inArea(nowY, nowX) && map[nowY][nowX] == 0) {
                map[nowY][nowX] = 1;
            }
            else {
                return false;
            }
            //다음 위치로 nowY, nowX 조정
            //합이 N 보다 크다는 것은 대각선 넘어로 갔다는 뜻
            if(nowY + nowX >= N-2) {
                if(nowX == N-1) {
                    nowX = nowY + 1;
                    nowY = N;
                }
                else {
                    nowX += 1;
                    nowY -= 1;
                }

            } else {
                if(nowY == 0) {
                    nowY = nowX + 1;
                    nowX = 0;
                }
                else {
                    nowY -= 1;
                    nowX += 1;
                }
            }
        }
        return true;
    }

    private static boolean inArea(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    private static boolean isNear(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int ny = y + y_moves[i];
            int nx = x + x_moves[i];
            if(inArea(ny, nx) && map[ny][nx] == 1) {
                return true;
            }
        }
        return false;
    }
}
