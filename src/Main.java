import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main{

    static BufferedReader br;
    static int n;
    static int[][] map;
    static int[] x_move = {-1, 1, 0, 0};
    static int[] y_move = {0, 0, 1, -1};
    static int min;
    static int max;
    static int answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        answer = 0;
        min = Integer.MAX_VALUE;
        max = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int left = 0;
        int right = max-min;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(findWay(mid)) {
                right = mid - 1;
                answer = mid;
            }
            else {
                left = mid + 1;
            }
        }
        System.out.print(answer);
    }

    //number 이하의 차이로 길을 찾을 수 있는지 확인해야 함.
    public static boolean findWay(int number) {
        //총 숫자의 범위를 확인해준다.
        for (int i = min; i+number <= max; i++) {
            //탐색을 진행하는 경우, 경로상 모든 숫자는 minNumber 이상 mxNumber 이하이다.
            int minNumber = i;
            int maxNumber = i + number;

            //시작 점이 범위 안에 들어오는지부터 확인한다.
            if(map[0][0] < minNumber || map[0][0] > maxNumber) {
                //범위 안에 들어오지 않으면 다음 반복문으로 넘어간다.
                continue;
            }
            Queue<Node> queue = new LinkedList<>();
            boolean[][] isVisited = new boolean[n][n];
            queue.offer(new Node(0, 0));
            isVisited[0][0] = true;

            while(!queue.isEmpty()) {
                Node nowNode = queue.poll();
                if(nowNode.y == (n-1) && nowNode.x == (n-1)) {
                    return true;
                }

                for (int j = 0; j < 4; j++) {
                    int newY = nowNode.y + y_move[j];
                    int newX = nowNode.x + x_move[j];
                    if(inArea(newY, newX) && !isVisited[newY][newX]
                            && minNumber <= map[newY][newX]
                            && map[newY][newX] <= maxNumber) {
                        queue.offer(new Node(newY, newX));
                        isVisited[newY][newX] = true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean inArea(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
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