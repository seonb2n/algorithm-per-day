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
    static char[][] map;
    static PriorityQueue<Node> findMineralQueue;
    static PriorityQueue<Node> tempQueue;
    static int N;

    //노드 탐색을 할 때, 4방향으로 움직일 수 있기 때문에 움직임 가능한 쌍을 배열로 넣어둔다.
    private static final int[] x_move = {1, 0, -1, 0};
    private static final int[] y_move = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        mapHeight = Integer.parseInt(st.nextToken());
        mapWidth = Integer.parseInt(st.nextToken());

        map = new char[mapHeight][mapWidth];
        findMineralQueue = new PriorityQueue<>();
        tempQueue = new PriorityQueue<>();

        for (int i = 0; i < mapHeight; i++) {
            String str = br.readLine();
            for (int j = 0; j < mapWidth; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            if(i % 2 == 0) {
                shootFromLeft(Integer.parseInt(st.nextToken()));
            }
            else {
                shootFromRight(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                sb.append(map[i][j]);
            }
            if(i != mapHeight-1) {
                sb.append("\n");
            }
        }

        System.out.println(sb);

    }

    private static void shootFromLeft(int height) {
        int heightY = mapHeight - height;
        for (int i = 0; i < mapWidth; i++) {
            if(map[heightY][i] == 'x') {
                map[heightY][i] = '.';
                checkMap(heightY, i);
                break;
            }
        }
    }

    private static void shootFromRight(int height) {
        int heightY = mapHeight - height;
        for (int i = mapWidth-1; i >= 0; i--) {
            if(map[heightY][i] == 'x') {
                map[heightY][i] = '.';
                checkMap(heightY, i);
                break;
            }
        }
    }


    private static void checkMap(int firstY, int firstX) {
        PriorityQueue<Node> tempQueue = new PriorityQueue<>();
        //부서진 노드의 밑의 노드를 탐색한다.

        int new_x;
        int new_y;
        for (int i = 0; i < 4; i++) {
            new_x = firstX + x_move[i];
            new_y = firstY + y_move[i];
            if(inArea(new_x, new_y) && map[new_y][new_x] == 'x') {
                findNode(new_y, new_x, tempQueue);
            }
            tempQueue.clear();
        }
    }

    static void findNode(int new_y, int new_x, PriorityQueue<Node> tempQueue) {
        boolean[][] tempVisited = new boolean[mapHeight][mapWidth];
        Node new_Node = new Node(new_x, new_y);
        findMineralQueue.offer(new_Node);
        tempQueue.offer(new_Node);
        tempVisited[new_y][new_x] = true;
        //비어 있지 않거나, peek 값이 바닥이 아니면 계속해서 탐색
        while (!findMineralQueue.isEmpty()) {
            Node n = findMineralQueue.poll();
            //연결된 노드를 탐색
            //꺼낸 노드가 바닥에 닿아있다면, 더이상 탐색할 필요가 없다.
            if(n.y == mapHeight - 1) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                new_x = n.x + x_move[i];
                new_y = n.y + y_move[i];
                if(inArea(new_x, new_y) && map[new_y][new_x] == 'x' && !tempVisited[new_y][new_x]) {
                    new_Node = new Node(new_x, new_y);
                    findMineralQueue.offer(new_Node);
                    tempQueue.offer(new_Node);
                    tempVisited[new_y][new_x] = true;
                }
            }
        }

        if(!tempQueue.isEmpty() && tempQueue.peek().y != mapHeight-1) {
            //tempQueue 에 존재하는 Node 들의 y 값을 내려야 한다.
            downNode(tempQueue);
        }

        tempQueue.clear();
        findMineralQueue.clear();
    }

    static void downNode(PriorityQueue<Node> downNodeQueue) {
        //밑에 미네랄에 붙는 경우를 구해야 한다.
        int downHeight = findMinimumHeight(downNodeQueue);
        //땅에 붙는 경우
        while (!tempQueue.isEmpty()) {
            Node downNode = tempQueue.poll();
            map[downNode.y + downHeight][downNode.x] = 'x';
            map[downNode.y][downNode.x] = '.';
        }
    }

    //해당 큐에서 땅이나 미네랄까지 가장 가까운 거리를 찾는 메서드
    private static int findMinimumHeight(PriorityQueue<Node> downNodeQueue) {
        int min = mapHeight - downNodeQueue.peek().y - 1;
        boolean[] isXVisited = new boolean[100];
        while (!downNodeQueue.isEmpty()) {
            Node n = downNodeQueue.poll();
            tempQueue.offer(n);
            if(!isXVisited[n.x]) {
                for (int i = n.y+1; i < mapHeight; i++) {
                    if(map[i][n.x] == 'x') {
                        min = Math.min(min, (i - n.y)-1);
                    }
                    if(i == mapHeight - 1 && map[i][n.x] == '.') {
                        min = Math.min(min, (i - n.y));
                    }
                }
                isXVisited[n.x] = true;
            }

        }
        return min;
    }

    static boolean inArea(int x, int y) {
        return x >= 0 && x < mapWidth && y >= 0 && y < mapHeight;
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //높이가 가장 낮은 객체가 우선순위 큐의 제일 앞에 위치한다.
        @Override
        public int compareTo(Node o) {
            if(this.y < o.y) {
                return 1;
            }
            else {
                return -1;
            }
        }
    }
}