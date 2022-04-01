import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int N;
    static int M;
    static int[][] map;
    static int[][] groupMap;
    static Queue<Node> queue;
    static int count;
    static int[] y_move = {-1, 1, 0, 0};
    static int[] x_move = {0, 0, 1, -1};
    static int[][] result;
    static HashMap<Integer, Integer> groups;
    static int groupId = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        groupMap = new int[N][M];
        result = new int[N][M];
        queue = new LinkedList<>();
        groups = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                if(map[i][j] == 1) {
                    queue.add(new Node(i, j));
                }
            }
        }

        //각각의 0에 대해서 인접한 0 끼리 그룹화를 해준다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //해당 지점이 0이고, 아직 그룹화되지 않은 장소라면
                if(map[i][j] == 0 && groupMap[i][j] == 0) {
                    groupId++;
                    count = 0;
                    BFS(i, j);
                    groups.put(groupId, count);
                }
            }
        }

        //각각의 벽에 대해서 4방향을 확인하면서 그룹의 개수를 세주면 된다.
        while (!queue.isEmpty()) {
            Node nowNode = queue.poll();
            int nowY = nowNode.y;
            int nowX = nowNode.x;
            int nowCount = 1;
            HashSet<Integer> set = new HashSet<>();

            for (int i = 0; i < 4; i++) {
                int newY = nowY + y_move[i];
                int newX = nowX + x_move[i];
                if(inArea(newY, newX) && map[newY][newX] != 1) {
                    set.add(groupMap[newY][newX]);
                }
            }

            for(Integer i : set) {
                nowCount += groups.get(i);
            }
            result[nowY][nowX] = nowCount % 10;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(result[i][j]);
            }
            if(i != N-1) {
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    //인접한 0을 탐색하며 해당 그룹맵을 최신화 시킨다.
    public static void BFS(int nowY, int nowX) {
        groupMap[nowY][nowX] = groupId;
        count++;

        for (int i = 0; i < 4; i++) {
            int newY = nowY + y_move[i];
            int newX = nowX + x_move[i];
            if(inArea(newY, newX) && groupMap[newY][newX] == 0 && map[newY][newX] == 0) {
                BFS(newY, newX);
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