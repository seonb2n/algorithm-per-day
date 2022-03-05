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
    static PriorityQueue<Node> countMeltQueue;
    static boolean[][] countMeltVisited;

    //노드 탐색을 할 때, 4방향으로 움직일 수 있기 때문에 움직임 가능한 쌍을 배열로 넣어둔다.
    private static final int[] x_move = {1, 0, -1, 0};
    private static final int[] y_move = {0, 1, 0, -1};

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
                    countMeltQueue.offer(new Node(j, i, 0));
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
                   countMeltQueue.offer(new Node(j, i, 0));
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
            Node node = countMeltQueue.poll();
            for (int i = 0; i < 4; i++) {
                int new_x = node.x + x_move[i];
                int new_y = node.y + y_move[i];

                //방문하지 않은 노드이면서, 얼음인 부분 탐색
                if(inArea(new_x, new_y) && !countMeltVisited[new_y][new_x] && map[new_y][new_x] == 1) {
                    //PriorityQueue 이므로 물에 가까운 얼음들부터 노드 탐색이 된다.
                    //아직 탐색이 되지 않았으므로 MAX VALUE 를 갖고 있을 것이다.
                    if(countMeltMap[new_y][new_x] > node.count+1) {
                        countMeltVisited[new_y][new_x] = true;
                        countMeltMap[new_y][new_x] = node.count+1;
                        countMeltQueue.offer(new Node(new_x, new_y, node.count+1));
                    }
                }
            }
        }
    }

    // 두 백조가 만나는 날을 계산
    private static int getDateToMeet() {
        Node swanA = new Node(swanAX, swanAY);
        Node swanB = new Node(swanBX, swanBY);

        return isWay(swanA, swanB);
    }

    //node1 에서 node2 까지 방문이 가능한지를 확인하는 함수
    //BFS 사용
    private static int isWay(Node node1, Node node2) {
        boolean[][] visited = new boolean[mapHeight][mapWidth];

        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.offer(node1);
        visited[node1.y][node1.x] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            //해당 지점이 두 번째 백조의 위치라면 해당 노드까지 오는데 걸린 최대 얼음의 결과를 반환한다.
            //count 의 오름차순으로 있는 우선 순위 큐를 사용하기 때문에, 가장 먼저 도착한 경우가 count 를 가장 덜 쓴 경우이다.
            if(node.y == node2.y && node.x == node2.x) {
                return node.count;
            }

            for (int k = 0; k < 4; k++) {
                //지정된 node 주위 4칸에 대해서 탐색한다.
                int new_x = node.x + x_move[k];
                int new_y = node.y + y_move[k];

                //범위에도 벗어나지 않고 방문을 안한 경우라면
                if (inArea(new_x, new_y) && !visited[new_y][new_x]) {
                    visited[new_y][new_x] = true;

                    //새로 방문하는 지점의 녹는 점이 더 높다면
                    if(countMeltMap[new_y][new_x] > node.count) {
                        queue.offer(new Node(new_x, new_y, countMeltMap[new_y][new_x]));
                    }
                    else {
                        queue.offer(new Node(new_x, new_y, node.count));
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static boolean inArea(int x, int y) {
        return x >= 0 && y >= 0 && x < mapWidth && y < mapHeight;
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int count;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        //얼음이 녹는데 걸리는 시간이 적은 객체가 우선순위 큐의 제일 앞에 위치한다.
        @Override
        public int compareTo(Node o) {
            if(this.count < o.count) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }
}