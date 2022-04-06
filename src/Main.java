import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static BufferedReader br;
    static int N;
    static int[][] rectangles;
    static int groupNumber;
    static int[] rectangleGroup;
    static boolean isStartAt;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rectangles = new int[N][4];
        rectangleGroup = new int[N];
        groupNumber = 0;
        isStartAt = false;

        for (int i = 0; i < N; i++) {
            rectangles[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            //아직 아이디를 부여 받지 못한 사각형이 있다면
            if(rectangleGroup[i] == 0) {
                groupNumber++;
                DFS(i, groupNumber);
            }
        }

        if(isStartAt) {
            groupNumber--;
        }

        System.out.println(groupNumber);
    }

    //탐색 중에 하나라도 0,0 에서 시작하는지
    public static void DFS(int nowRectangle, int nowGroup) {
        int x1 = rectangles[nowRectangle][0];
        int y1 = rectangles[nowRectangle][1];
        int x2 = rectangles[nowRectangle][2];
        int y2 = rectangles[nowRectangle][3];

        if(!isStartAt && isZeroAt(x1, y1, x2, y2)) {
            isStartAt = true;
        }

        rectangleGroup[nowRectangle] = nowGroup;

        for (int i = 0; i < N; i++) {
            if(i == nowRectangle) {
                continue;
            }
            //자신과 겹치는 사각형을 찾는다.
            if(isRectangleSameGroup(nowRectangle, i) && rectangleGroup[i] != nowGroup) {
                //겹쳐져 있으면 해당 사각형에 그룹 아이디를 부여하고, 그 사각형과 겹치는 사각형을 찾는다.
                DFS(i, nowGroup);
            }
        }
    }

    public static boolean isZeroAt(int x1, int y1, int x2, int y2) {
        if(x1 <= 0 && 0 <= x2 && y1 == 0) {
            return true;
        }
        if(x1 == 0 && y1 <= 0 && 0 <= y2) {
            return true;
        }
        if(x2 == 0 && y1 <= 0 && 0 <= y2) {
            return true;
        }
        if(x1 <= 0 && 0 <= x2 && y2 == 0) {
            return  true;
        }

        return false;
    }

    //사각형 4개의 점 중 하나라도 다른 사각형의 내부에 존재하면 된다.
    //단 4개 모두 존재해서는 안된다.
    public static boolean isRectangleSameGroup(int firstRectangle, int secondRectangle) {
        int x1 = rectangles[firstRectangle][0];
        int y1 = rectangles[firstRectangle][1];
        int x2 = rectangles[firstRectangle][2];
        int y2 = rectangles[firstRectangle][3];

        int x3 = rectangles[secondRectangle][0];
        int y3 = rectangles[secondRectangle][1];
        int x4 = rectangles[secondRectangle][2];
        int y4 = rectangles[secondRectangle][3];

        if((x1 < x3 && x4 < x2 && y1 < y3 && y4 < y2)
                || (x1 > x3 && x4 > x2 && y1 > y3 && y4 > y2)
                || x2 < x3 || x4 < x1 || y2 < y3 || y4 < y1){
            return false;
        }
        return true;
    }

}