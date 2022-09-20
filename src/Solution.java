import java.util.*;

class Solution {
    static int w;
    static int h;

    public static void main(String[] args) {
        int[][] rc = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] ops = {"Rotate ", "ShiftRow"};
        solution(rc, ops);
    }

    public static int[][] solution(int[][] rc, String[] operations) {
        h = rc.length;
        w = rc[0].length;

        int[][] answer = rc;

        for (String op : operations) {
            if (op.equals("Rotate")) {
                answer = rotate(answer);
            }
            else {
                answer = shiftRow(answer);
            }
        }
        return answer;
    }

    public static int[][] shiftRow(int[][] rc) {
        int[] firstLane = rc[rc.length-1].clone();

        for (int i = rc.length-2; i >= 0; i--) {
            rc[i+1] = rc[i].clone();
        }
        rc[0] = firstLane;
        return rc;
    }

    public static int[][] rotate(int[][] rc) {
        int B = rc[0][w-1];
        int C = rc[h-1][0];
        int D = rc[h-1][w-1];

        //윗줄과 밑줄 처리
        for (int i = w-2; i >= 0; i--) {
            rc[0][i+1] = rc[0][i];
            rc[h-1][w-2-i] = rc[h-1][w-2-i+1];

        }
        rc[h-1][w-2] = D;

        //오른쪽 왼쪽 줄 처리
        for (int i= h-2; i >= 1; i--) {
            rc[i+1][w-1] = rc[i][w-1];
            rc[h-2-i][0] = rc[h-2-i+1][0];
        }
        rc[1][w-1] = B;
        rc[h-2][0] = C;
        return rc;
    }
}

