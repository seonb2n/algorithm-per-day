import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int mapWidth;
    static int mapHeight;
    static int[][] map;
    static int[][] tempWayMap;

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
        tempWayMap = new int[mapHeight][mapWidth];

        boolean isFirst = false;

        for (int i = 0; i < mapHeight; i++) {
            String[] str = br.readLine().split("");
            for (int j = 0; j < str.length; j++) {
                String temp = str[j];
                //물은 0
                if(temp.equals(".")) {
                    map[i][j] = 0;
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
                }
            }
        }

        //map 의 모든 얼음을 몇 턴 뒤에 녹을 얼음일지로 치환한다.
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                //해당 칸이 얼음일 때
                if(map[i][j] == 1) {
                    //주위 4칸이 모두 1이상이면, 가장 가까운 0 까지의 거리로 해당 값이 정해짐
                    int temp = getLeft(i,j);
                    if(temp > 1) {
                       map[i][j] = temp;
                       temp = getRight(i, j);
                       if(temp > 1) {
                           map[i][j] = Math.min(temp, map[i][j]);
                           temp = getTop(i, j);
                           if(temp > 1) {
                               map[i][j] = Math.min(temp, map[i][j]);
                               temp = getBottom(i, j);
                               if(temp > 1) {
                                   map[i][j] = Math.min(temp, map[i][j]);
                               }  else {
                                   map[i][j] = 1;
                               }
                           } else {
                               map[i][j] = 1;
                           }
                       } else {
                           map[i][j] = 1;
                       }
                    }
                }
            }
        }

        //자기 주변 얼음을 확인해서 해당 값의 최솟값 + 1 로 값을 바꾼다.
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j <mapWidth; j++) {
                if (map[i][j] != 0) {
                    map[i][j] = getNearMinValue(i, j)+1;
                }
            }
        }

        System.out.println(getDateToMeet());

    }

    private static int getNearMinValue(int i, int j) {
        int top = 1501;
        int bottom = 1501;
        int left = 1501;
        int right = 1501;
        if(i+1 < mapHeight) {
            top = map[i+1][j];
        }
        if(i-1 >= 0) {
            bottom = map[i-1][j];
        }
        if(j+1 < mapWidth) {
            right = map[i][j+1];
        }
        if(j-1 >= 0) {
            left = map[i][j-1];
        }

       return Math.min(Math.min(top, bottom), Math.min(left, right));
    }

    //i, j 에서 밑으로 가까운 0까지의 거리를 구하는 함수
    private static int getBottom(int i, int j) {
        if(i == mapHeight-1) {
            return 1501;
        }
        int result = 0;
        while(i<mapHeight && map[i][j] != 0) {
            result++;
            i++;
        }

        return result;
    }

    private static int getTop(int i, int j) {
        if(i == 0) {
            return 1501;
        }
        int result = 0;
        while(i>=0 && map[i][j] != 0) {
            result++;
            i--;
        }

        return result;
    }

    private static int getRight(int i, int j) {
        if(j == mapWidth-1) {
            return 1501;
        }
        int result = 0;
        while(j<mapWidth && map[i][j] != 0) {
            result++;
            j++;
        }

        return result;
    }

    private static int getLeft(int i, int j) {
        if(j == 0) {
            return 1501;
        }
        int result = 0;
        while(j>=0 && map[i][j] != 0) {
            result++;
            j--;
        }

        return result;
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
                    if(map[new_y][new_x] > point.count) {
                        queue.offer(new Point(new_x, new_y, map[new_y][new_x]));
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