import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static int[][] map;
    static Queue<Node> queue;
    static int startY = 7;
    static int startX = 0;
    static boolean isWay;
    static int[] move_x = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int[] move_y = {-1, -1, -1, 0, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        map = new int[8][8];
        queue = new LinkedList<>();
        isWay = false;

        for (int i = 0; i < 8; i++) {
            String[] str = br.readLine().split("");
            for (int k = 0; k < 8; k++) {
                if(str[k].equals("#")) {
                    map[i][k] = 1;
                    queue.add(new Node(i, k));
                }
                else {
                    map[i][k] = 0;
                }
            }
        }

        //bfs 로 각 칸이 몇 턴 뒤에 벽으로 변하는지 구해야 함.
        BFS();

        //dfs 로 경로를 탐색한다. 9턴 동안 살아있기만 하면 된다.
        DFS(1, startY, startX);

        if(isWay) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
    }

    //각 칸이 몇 턴 뒤에 노드로 변하는지 계산한다.
    public static void BFS() {
        int nowTurn = 2;

        while (!queue.isEmpty()) {
            int nowSize = queue.size();
            for (int i = 0; i < nowSize; i++) {
                Node nowNode = queue.poll();

                //지금의 벽이 아래로 한 칸씩 이동한다.
                int nowY = nowNode.y;
                int nowX = nowNode.x;

                if((nowY + 1) < 8) {
                    if(map[nowY+1][nowX] != 0) {
                        String temp = String.valueOf(map[nowY+1][nowX]);
                        temp = temp + nowTurn;
                        map[nowY+1][nowX] = Integer.parseInt(temp);
                    }
                    else {
                        map[nowY+1][nowX] = nowTurn;
                    }
                    queue.add(new Node(nowY+1, nowX));
                }
            }
            nowTurn++;
        }
    }

    public static void DFS(int nowTurn, int nowY, int nowX) {
        if(nowTurn == 9) {
            isWay = true;
        }
        else {
            for (int i = 0; i < 9; i++) {
                int newY = nowY+move_y[i];
                int newX = nowX+move_x[i];
                if(inArea(newY, newX)) {
                    int[] temp = Arrays.stream(String.valueOf(map[newY][newX]).split("")).mapToInt(Integer::new).toArray();
                    //nowTurn 이 temp 의 값에 해당 사항이 없을 때만 다음 단계로 탐색을 할 수 있다.
                    boolean isContain = false;
                    for (int j = 0; j < temp.length; j++) {
                        if(temp[j] == nowTurn || temp[j] == nowTurn+1) {
                            isContain = true;
                            break;
                        }
                    }
                    if(!isContain) {
                        DFS(nowTurn+1, newY, newX);
                    }
                }
            }
        }
    }

    public static boolean inArea(int y, int x) {
        return 0<= y && y < 8 && 0 <= x && x < 8;
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