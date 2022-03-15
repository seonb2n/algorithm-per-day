import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static int mapHeight;
    static int mapWidth;
    static int[][] map;
    static List<Node> dirtPoints;
    static Node clearStart;
    static int minWay;
    static int[] x_move = {-1, 1 , 0, 0};
    static int[] y_move = {0, 0, 1, -1};
    static int[][] dirtDistance;

    public static void main(String[]args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        dirtPoints = new ArrayList<>();
        while (true) {
             int[] mapSize = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
             mapWidth = mapSize[0];
             mapHeight = mapSize[1];
             if(mapHeight == 0 && mapWidth == 0) {
                 break;
             }
             //깨끗한 칸은 0
             //더러운 칸은 1
             // 가구는 2
             map = new int[mapHeight][mapWidth];
            for (int i = 0; i < mapHeight; i++) {
                String[] line = br.readLine().split("");
                for (int j = 0; j < mapWidth; j++) {
                    if(line[j].equals("*")) {
                        map[i][j] = 1;
                        dirtPoints.add(new Node(i, j, true));
                    }
                    else if(line[j].equals("x")) {
                        map[i][j] = 2;
                    }
                    else if(line[j].equals("o")) {
                        dirtPoints.add(0, new Node(i, j, false));
                        clearStart = new Node(i, j, false);
                    }
                }
            }
            sb.append(findMinWay());
            sb.append("\n");

            dirtPoints.clear();
        }
        System.out.println(sb);
    }

    private static int findMinWay() {
        minWay = Integer.MAX_VALUE;
        //map 을 탐색하면서 최소한의 경로로 목표에 도달해야 한다.
        dirtDistance = new int[dirtPoints.size()][dirtPoints.size()];
        //청소기와 각 먼지, 먼지와 먼지들 사이의 거리를 BFS 로 구해줘야 한다.
        for (int i = 0; i < dirtPoints.size() + 1; i++) {
            for (int j = i+1; j < dirtPoints.size(); j++) {
                int temp = BFS(i, j);
                if(temp == 0) {
                    return -1;
                }
                dirtDistance[i][j] = temp;
                dirtDistance[j][i] = temp;
            }
        }

        //dirtDistance를 바탕으로 최솟값을 가질 수 있는 경로를 찾는다.

        //방문한 지점이 담겨 있는 배열
        boolean[] dirtList = new boolean[dirtPoints.size()];
        dirtList[0] = true;

        findWay(0, 0, dirtList);

        return minWay;
    }

    //DFS 로 최소 경로 탐색
    private static void findWay(int nowValue, int nowNode, boolean[] dirtList) {
        boolean allVisit = true;

        for (int i = 0; i < dirtList.length; i++) {
            if(!dirtList[i]) {
                allVisit = false;
                int temp = nowValue + dirtDistance[nowNode][i];
                boolean[] tempDirtList = dirtList.clone();
                tempDirtList[i] = true;
                findWay(temp, i, tempDirtList);
            }
        }

        if(allVisit) {
            minWay = Math.min(nowValue, minWay);
        }
    }

    //탐색을 해서 startNode 에서 각 지점까지 가는 최솟값을 구한다.
    private static int BFS(int startNode, int endNode) {
        Queue<Node> bfsQueue = new LinkedList<>();
        boolean[][] isVisited = new boolean[mapHeight][mapWidth];

        Node fromNode = dirtPoints.get(startNode);
        Node toNode = dirtPoints.get(endNode);

        bfsQueue.offer(fromNode);
        isVisited[dirtPoints.get(startNode).y][dirtPoints.get(startNode).x] = true;

        int farFromStart = 0;
        while (!bfsQueue.isEmpty()) {

            //한칸 움직일 때마다 해당 큐에 있는 노드들은 모두 원점으로부터 동일한 거리에 있다.
            //동일한 거리에 있는 노드에 대한 처리를 진행한다.
            int size = bfsQueue.size();
            for (int i = 0; i < size; i++) {
                Node now_Node = bfsQueue.poll();
                if(now_Node.y == toNode.y && now_Node.x == toNode.x) {
                    return farFromStart;
                }

                for (int j = 0; j < 4; j++) {
                    int new_y = now_Node.y + y_move[j];
                    int new_x = now_Node.x + x_move[j];
                    if(inArea(new_y, new_x) && map[new_y][new_x] != 2 && !isVisited[new_y][new_x]) {
                        boolean isDirt = false;
                        if(map[new_y][new_x] == 1) {
                            isDirt = true;
                        }
                        Node new_Node = new Node(new_y, new_x, isDirt);
                        bfsQueue.offer(new_Node);
                        isVisited[new_y][new_x] = true;
                    }
                }
            }
            farFromStart++;
        }

        return 0;
    }

    private static boolean inArea(int y, int x) {
        return y >= 0 && y < mapHeight && x >= 0 && x < mapWidth;
    }
    static class Node{
        int y;
        int x;
        boolean isDirt;


        public Node(int y, int x, boolean isDirt) {
            this.y = y;
            this.x = x;
            this.isDirt = isDirt;
        }
    }
}