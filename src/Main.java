import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static BufferedWriter bw;
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
    static List<Node> paths;

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
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
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

        // 1. A 의 최단 경로를 BFS 로 탐색, 탐색 후에 경로 마킹, 이때 경로상에 B 인 점이 있으면 안된다.
        int[][] map = new int[N+1][M+1];
        map[bx1][by1] = -1;
        map[bx2][by2] = -1;
        int firstMinA = BFS(ax1, ay1, ax2, ay2, map);

        // 2. B 의 최단 경로를 BFS 로 탐색하되, A 의 경로는 못 지나감
        map = new int[N+1][M+1];
        map[ax1][ay1] = -1;
        map[ax2][ay2] = -1;
        for (Node path : paths) {
            map[path.x][path.y] = -1;
        }
        int firstMinB = BFS(bx1, by1, bx2, by2, map);

        // 3. 1, 2 의 과정을 B 를 먼저 탐색하는 것으로
        map = new int[N+1][M+1];
        map[ax1][ay1] = -1;
        map[ax2][ay2] = -1;
        int secondMinB = BFS(bx1, by1, bx2, by2, map);

        map = new int[N+1][M+1];
        map[bx1][by1] = -1;
        map[bx2][by2] = -1;
        for (Node path : paths) {
            map[path.x][path.y] = -1;
        }
        int secondMinA = BFS(ax1, ay1, ax2, ay2, map);

        // 4. 1,2 의 결과와 3의 결과를 비교해서 작은 쪽으로 답 제출
        if (firstMinA * firstMinB == 0 && secondMinA * secondMinB == 0) {
            bw.write(imp);
        }
        else {
            // 둘 중 하나라도 0 이면 정답이 될 수 없다.
            if (firstMinA * firstMinB == 0) {
                bw.write(String.valueOf(secondMinA + secondMinB));
            }
            else if (secondMinA * secondMinB == 0) {
                bw.write(String.valueOf(firstMinA + firstMinB));
            }
            else {
                bw.write(String.valueOf(Math.min(firstMinA + firstMinB, secondMinA + secondMinB)));
            }
        }
        bw.flush();
        bw.close();
    }


    static int BFS(int startX, int startY, int targetX, int targetY, int[][] map) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(startX, startY, 0));

        while (!queue.isEmpty()) {
            Node nowNode = queue.poll();

            if (nowNode.x == targetX && nowNode.y == targetY) {
                break;
            }

            int nowVal = map[nowNode.x][nowNode.y];
            for (int i = 0; i < 4; i++) {
                int nextX = nowNode.x + x_key[i];
                int nextY = nowNode.y + y_key[i];

                // 해당 지역이 통행 가능 지역인 경우
                if (!(nextX == startX && nextY == startY) && inArea(nextX, nextY) && map[nextX][nextY] != -1) {
                    // 해당 지역이 처음 방문하는 지역인 경우
                    if (map[nextX][nextY] == 0) {
                        map[nextX][nextY] = nowVal + 1;
                        queue.offer(new Node(nextX, nextY, nowVal + 1));
                    }
                    // 이미 이전에 방문한 지역인 경우, 이번 방문이 더 빠른 경우에만 업데이트된다.
                    else {
                        if (map[nextX][nextY] > nowVal + 1) {
                            map[nextX][nextY] = nowVal + 1;
                            queue.offer(new Node(nextX, nextY, nowVal + 1));
                        }
                    }
                }
            }
        }
        paths = new ArrayList<>();
        // 목표에 도달할 수 없다면 0 을 반환한다.
        if (map[targetX][targetY] <= 0) {
            return 0;
        }
        // 경로를 역 추적해서 A 의 경로를 확인한다
        int nowX = targetX;
        int nowY = targetY;
        int nowVal = map[nowX][nowY];
        while (!(nowX == startX && nowY == startY)) {
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + x_key[i];
                int nextY = nowY + y_key[i];
                if (inArea(nextX, nextY) && map[nextX][nextY] == nowVal - 1) {
                    nowX = nextX;
                    nowY = nextY;
                    nowVal = map[nextX][nextY];
                    paths.add(new Node(nextX, nextY, 0));
                    break;
                }
            }
        }

        return map[targetX][targetY];
    }

    static boolean inArea(int nowX, int nowY) {
        return 0 <= nowX && nowX <= N && 0 <= nowY && nowY <= M;
    }

    public static class Node implements Comparable<Node> {
        int x;
        int y;
        int val;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
}
