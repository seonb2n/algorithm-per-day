import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N;
    static int M;
<<<<<<< HEAD
    static BufferedReader br;
    static Line[] lines;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lines = new Line[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            lines[i] = new Line(Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()));
        }



    }

    public static int DFS(int destination, int nowNode, int nowTime) {
        if(nowNode == destination) {
            return nowTime;
        }
        
    }

    public static class Line {
        int from;
        int to;
        int time;

        public Line(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
=======
    static int K;
    static char[][] map;
    static int[][][] dp;
    static String targetWord;
    static int wordSize;
    static int answer;
    static int[] x_move = {-1, 1, 0, 0};
    static int[] y_move = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        targetWord = br.readLine();
        wordSize = targetWord.length();
        answer = 0;
        //해당 지점을 방문할 때, 단어를 완결지을 수 있는 경로가 몇 개 존재하는지 알려주는 dp
        dp = new int[N][M][wordSize];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //0번째 자리에 있는 숫자부터 탐색 시작
                answer += DFS(i, j, 0);
            }
        }

        System.out.println(answer);
    }

    public static int DFS(int nowY, int nowX, int target) {
        //끝까지 도달한 경우
        if (target == wordSize - 1) {
            //dp 값이 1이면 방문했을 때 단어가 완성될 수 있다. 2이면 방문했는데 단어가 없다. -1이면 아직 방문하지 않았다. 0 이면 찾는 글자가 아니다.
            return dp[nowY][nowX][target] = 1;
        }
        if (dp[nowY][nowX][target] != -1) {
            //이미 방문했던 지점을 탐색하는 경우 해당 dp 를 반환해준다.
            return dp[nowY][nowX][target];
        }
        if (map[nowY][nowX] != targetWord.charAt(target)) {
            //방문했는데 해당 글자가 찾는 글자가 아니다.
            return dp[nowY][nowX][target] = 0;
        }

        //dp가 -1 이면 처음 방문한 경우이기 때문에 해당 지점에서부터 단어를 완성할 수 있는 경우가 몇개인지를 채워야 한다.
        dp[nowY][nowX][target] = 0;
        char nextTarget = targetWord.charAt(target + 1);
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= K; j++) {
                int newX = nowX + x_move[i] * j;
                int newY = nowY + y_move[i] * j;
                if (inArea(newY, newX) && map[newY][newX] == nextTarget) {
                    dp[nowY][nowX][target] += DFS(newY, newX, target + 1);
                }
            }
        }
        return dp[nowY][nowX][target];
    }

    public static boolean inArea(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
>>>>>>> 8cbdcefc233ad4303a2b83f5b93e8810cb84aaa4
    }
}