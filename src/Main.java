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
    static int[][] tempWayMap;
    static int swanAX;
    static int swanAY;

    static int swanBX;
    static int swanBY;

    static int day = 0;

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

        System.out.println(map);

        System.out.println(getDateToMeet());

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


    // 두 백조가 만나는 날을 계산하는 프로그램
    private static int getDateToMeet() {

        if(isSwanCanMeet()) {
            return 0;
        }

        while (!isSwanCanMeet()) {
            changeDay();
        }

        return day;
    }

    //시간이 지나서 얼음이 녹은 형태로 구현
    private static void changeDay() {
        day++;
        int[][] tempMap = new int[mapHeight][mapWidth];
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                //해당 칸이 얼음일 때, 주변에 물이 하나라도 있으면 물로 바꾼다.
                if(tempMap[i][j] == 1) {
                    if(i+1<mapHeight && tempMap[i+1][j] == 0) {
                        map[i][j] = 0;
                    }
                    else if(i-1 >= 0 && tempMap[i-1][j] == 0) {
                        map[i][j] = 0;
                    }
                    else if(j+1<mapWidth && tempMap[i][j+1] == 0) {
                        map[i][j] = 0;
                    }
                    else if(j-1 >= 0 && tempMap[i][j-1] == 0) {
                        map[i][j] = 0;
                    }
                }
            }
        }
    }

    //백조가 서로 만날 수 있는지를 탐색하는 알고리즘
    private static boolean isSwanCanMeet() {
        //1인 경우는 물이기 때문에 통행이 가능하다.
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                tempWayMap[i][j] = map[i][j];
            }
        }

        return isWay(swanAX, swanAY);
    }

    //현재 위치에서 목표 지점인 swanBX, swanBY 까지 가는 경로가 있는지 탐색하는 알고리즘
    //지나온 길을 다시 지나서는 안된다.
    private static boolean isWay(int nowX, int nowY) {
        if(nowX == swanBX && nowY == swanBY) {
            return true;
        }

        tempWayMap[nowY][nowX] = -1;

        //오른쪽 칸 탐색
        if(nowX + 1 < mapWidth && tempWayMap[nowY][nowX+1] == 0) {
            return isWay(nowX+1, nowY);
        }

        //왼쪽 칸 탐색
        if(nowX - 1 >= 0 && tempWayMap[nowY][nowX-1] == 0) {
            return isWay(nowX-1, nowY);
        }

        //아래쪽 칸 탐색
        if(nowY + 1 < mapHeight && tempWayMap[nowY+1][nowX] == 0) {
            return isWay(nowX, nowY+1);
        }

        //위쪽 칸 탐색
        if(nowY - 1 >= 0 && tempWayMap[nowY-1][nowX] == 0) {
            return isWay(nowX, nowY-1);
        }

        return false;
    }
}