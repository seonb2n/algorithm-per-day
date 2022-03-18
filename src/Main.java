import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int testCaseNumber;
    static int interNumber;
    static int roadNumber;
    static int destinationNumber;
    static int startPoint;
    static int crossedPoint1;
    static int crossedPoint2;
    static int[][] map;
    static int[] targetPoint;
    static boolean[] isCrossedToMin;
    static int minDistance;

    public static void main(String[]args) throws IOException {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));

        testCaseNumber = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCaseNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            interNumber = Integer.parseInt(st.nextToken());
            roadNumber = Integer.parseInt(st.nextToken());
            destinationNumber = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            startPoint = Integer.parseInt(st.nextToken());
            crossedPoint1 = Integer.parseInt(st.nextToken());
            crossedPoint2 = Integer.parseInt(st.nextToken());
            map = new int[interNumber+1][interNumber+1];

            for (int j = 0; j < roadNumber; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int point1 = Integer.parseInt(st.nextToken());
                int point2 = Integer.parseInt(st.nextToken());
                int distance = Integer.parseInt(st.nextToken());
                map[point1][point2] = distance;
                map[point2][point1] = distance;
            }

            targetPoint = new int[destinationNumber];
            for (int j = 0; j < destinationNumber; j++) {
                targetPoint[j] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(targetPoint);

            isCrossedToMin = new boolean[interNumber+1];
            //startPoint 에서 targetPoint 까지 가는 경로의 최솟값을 구한다.
            for (int j = 0; j < destinationNumber; j++) {
                minDistance = Integer.MAX_VALUE;
                boolean[][] isVisited = new boolean[interNumber+1][interNumber+1];
                DFS(targetPoint[j], startPoint, 0, false, isVisited);
                if(isCrossedToMin[targetPoint[j]]) {
                    sb.append(targetPoint[j]);
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    //startPoint 에서 targetPoint 의 각 지점까지 가는 최소 경로를 구한다.
    //해당 경로에 지나간 경로가 포함되는지를 탐색한다. 포함되어 있는 경우에만 minDistance 를 업데이트한다.
    private static void DFS(int targetPoint, int nowPoint, int nowDistance, boolean isCrossed, boolean[][] isVisited) {
        if(nowPoint == targetPoint) {
            if(minDistance > nowDistance) {
                if(isCrossed) {
                    minDistance = nowDistance;
                    isCrossedToMin[targetPoint] = true;
                }
                else {
                    minDistance = nowDistance;
                    isCrossedToMin[targetPoint] = false;
                }
            }
        }
        else {
            //탐색 실시
            boolean[][] tempVisited = new boolean[interNumber+1][interNumber+1];
            for (int i = 0; i < isVisited.length; i++) {
                tempVisited[i] = isVisited[i].clone();
            }
            for (int i = 1; i <= interNumber; i++) {
                if(map[nowPoint][i] != 0 && !tempVisited[nowPoint][i]) {
                    tempVisited[nowPoint][i] = true;
                    tempVisited[i][nowPoint] = true;
                    boolean tempCrossed = isCrossed;
                    if((crossedPoint1 == nowPoint && crossedPoint2 == i) || (crossedPoint1 == i && crossedPoint2 == nowPoint)){
                        tempCrossed = true;
                    }
                    DFS(targetPoint, i, nowDistance+map[nowPoint][i], tempCrossed, tempVisited);
                }
            }
        }
    }
}

