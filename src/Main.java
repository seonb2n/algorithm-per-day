import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int mapWidth;
    static int mapHeight;
    static int[][] map;
    static int[][] countMeltMap;
    static PriorityQueue<Point> countMeltQueue;
    static boolean[][] countMeltVisited;

    private static int[] x_move = {1, 0, -1, 0};
    private static int[] y_move = {0, 1, 0, -1};
    static int swanAX;
    static int swanAY;
    static int swanBX;
    static int swanBY;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        mapHeight = Integer.parseInt(st.nextToken());
        mapWidth = Integer.parseInt(st.nextToken());

        map = new int[mapHeight][mapWidth];
        countMeltMap = new int[mapHeight][mapWidth];
        countMeltQueue = new PriorityQueue<>();
        countMeltVisited = new boolean[mapHeight][mapWidth];

        boolean isFirst = false;

        for (int i = 0; i < mapHeight; i++) {
            Arrays.fill(countMeltMap[i], Integer.MAX_VALUE);

            String[] str = br.readLine().split("");
            for (int j = 0; j < str.length; j++) {
                String temp = str[j];
                //물은 0
                if(temp.equals(".")) {
                    map[i][j] = 0;
                    countMeltMap[i][j] = 0;
                    countMeltQueue.offer(new Point(j, i, 0));
                }
                //얼음은 1
                else if(temp.equals("X")) {
                    map[i][j] = 1;
                }
                else {
                   if(!isFirst) {
                       swanAY = i;
                       swanAX = j;
                       isFirst = true;
                   }
                   else {
                       swanBY = i;
                       swanBX = j;
                   }
                   map[i][j] = 0;
                   countMeltMap[i][j] = 0;
                   countMeltQueue.offer(new Point(j, i, 0));
                }
            }
        }

        //BFS 로 해당 지점의 얼음이 며칠 뒤에 녹을지 계산해야 한다.
        countMeltDay();
        System.out.println(getDateToMeet());

    }

    private static void countMeltDay() {
        //큐에는 물만 들어가 있다. 즉 물로부터 얼음에 대해서 노드 탐색을 진행한다.
        while (!countMeltQueue.isEmpty()) {
            Point point = countMeltQueue.poll();
            for (int i = 0; i < 4; i++) {
                int new_x = point.x + x_move[i];
                int new_y = point.y + y_move[i];

                //방문하지 않은 노드이면서, 얼음인 부분 탐색
                if(inArea(new_x, new_y) && !countMeltVisited[new_y][new_x] && map[new_y][new_x] == 1) {
                    //PriorityQueue 이므로 물에 가까운 얼음들부터 노드 탐색이 된다.
                    //아직 탐색이 되지 않았으므로 MAX VALUE 를 갖고 있을 것이다.
                    if(countMeltMap[new_y][new_x] > point.count+1) {
                        countMeltVisited[new_y][new_x] = true;
                        countMeltMap[new_y][new_x] = point.count+1;
                        countMeltQueue.offer(new Point(new_x, new_y, point.count+1));
                    }
                }
            }
        }
    }


    //해당 좌표에서 밑에쪽의 0까지 거리를 구하는 함수
    // 두 백조가 만나는 날을 계산
    private static int getDateToMeet() {
        Point swanA = new Point(swanAX, swanAY);
        Point swanB = new Point(swanBX, swanBY);

        return isWay(swanA, swanB);
    }

    //point1 에서 point2 까지 지정된 날 안에 방문이 가능한지를 확인하는 함수
    //BFS 사용
    private static int isWay(Point point1, Point point2) {
        boolean[][] visited = new boolean[mapHeight][mapWidth];

        PriorityQueue<Point> queue = new PriorityQueue<Point>();
        queue.offer(point1);
        visited[point1.y][point1.x] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            //해당 지점이 point2라면 결과를 반환한다.
            if(point.y == point2.y && point.x == point2.x) {
                return point.count;
            }

            for (int k = 0; k < 4; k++) {
                //지정된 point 주위 4칸에 대해서 탐색한다.
                int new_x = point.x + x_move[k];
                int new_y = point.y + y_move[k];

                //범위에도 벗어나지 않고 방문을 안한 경우라면
                if (inArea(new_x, new_y) && !visited[new_y][new_x]) {
                    visited[new_y][new_x] = true;

                    //새로 방문하는 지점의 녹는 점이 더 높다면
                    if(countMeltMap[new_y][new_x] > point.count) {
                        queue.offer(new Point(new_x, new_y, countMeltMap[new_y][new_x]));
                    }
                    else {
                        queue.offer(new Point(new_x, new_y, point.count));
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static boolean inArea(int x, int y) {
        return x >= 0 && y >= 0 && x < mapWidth && y < mapHeight;
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int count;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        //얼음이 녹는데 걸리는 시간이 적은 객체가 우선순위 큐의 제일 앞에 위치한다.
        @Override
        public int compareTo(Point o) {
            if(this.count < o.count) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }
}