import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int N;
    static int M;
    static int[][] map;
    static Queue<Node> queue;
    static int count;
    static boolean[][] isVisited;
    static int[] y_move = {-1, 1, 0, 0};
    static int[] x_move = {0, 0, 1, -1};
    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        result = new int[N][M];
        queue = new LinkedList<>();


        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                if(map[i][j] == 1) {
                    queue.add(new Node(i, j));
                }
            }
        }

        //각각의 벽에 대해서 벽을 부수고 이동할 수 있는 칸의 개수를 DFS 로 탐색한다.
        while (!queue.isEmpty()) {
            count = 1;
            isVisited = new boolean[N][M];
            Node nowNode = queue.poll();
            DFS(nowNode.y, nowNode.x);
            result[nowNode.y][nowNode.x] = count;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void DFS(int nowY, int nowX) {
        for (int i = 0; i < 4; i++) {
            int newY = nowY + y_move[i];
            int newX = nowX + x_move[i];
            if(inArea(newY, newX) && !isVisited[newY][newX] && map[newY][newX] == 0) {
                count++;
                isVisited[newY][newX] = true;
                DFS(newY, newX);
            }
        }
    }

    public static boolean inArea(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    public static class Node {
        int y;
        int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}