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
    static int[][] dp;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        mapHeight = Integer.parseInt(st.nextToken());
        mapWidth = Integer.parseInt(st.nextToken());

        map = new int[mapHeight][mapWidth];
        dp = new int[mapHeight][mapWidth];

        //dp : 시작 지점에서 i,j 를 거쳐서 목표 지점까지 가는 경우의 수에 대한 메모
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                dp[i][j] = -1;
            }
        }

        for (int i = 0; i < mapHeight; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int result = findWay(mapHeight-1, mapWidth-1);
        System.out.println(result);
    }

    //시작 지점에서 Y, X 를 거쳐서 목표 지점까지 가는 경우의 수를 구하는 함수
    private static int findWay(int pointY, int pointX) {
        if(pointY == 0 && pointX==0 ) {
            return 0;
        }

        if(dp[pointY][pointX] != -1) {
            return dp[pointY][pointX];
        }

        else {
            //시작 지점에서 해당 포인트까지 오는 경우의 수를 구해야 한다.
            //시작 지점에서 한 칸 떨어진 경우고 아직 탐색을 안한 부분이라면
            if((pointY+pointX) == 1) {
                if(map[pointY][pointX] < map[0][0]) {
                    dp[pointY][pointX] = 1;
                } else {
                    dp[pointY][pointX] = 0;
                }
            }

            else {
                int tempSum = 0;
                if(pointY != 0 && map[pointY-1][pointX] > map[pointY][pointX]) {
                    tempSum += findWay(pointY-1, pointX);
                }
                if(pointX != 0 && map[pointY][pointX-1] > map[pointY][pointX]) {
                    tempSum += findWay(pointY, pointX -1);
                }

                if(pointX+1 < mapWidth && map[pointY][pointX+1] > map[pointY][pointX]) {
                    tempSum += findWay(pointY, pointX+1);
                }

                if(pointY+1 < mapHeight && map[pointY+1][pointX] > map[pointY][pointX]) {
                    tempSum += findWay(pointY+1, pointX);
                }

                dp[pointY][pointX] = tempSum;
            }

            return dp[pointY][pointX];
        }
    }
}