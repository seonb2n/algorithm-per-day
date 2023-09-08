import java.util.*;

class Solution {

    static int minCost = Integer.MAX_VALUE;

    static int[] x_moves = {-1, 0, 1, 0};
    static int[] y_moves = {0, 1, 0, -1};
    static int[] dir = {1, 2, 3, 4};
    static int N;
    static boolean[][] isPassed;
    public int solution(int[][] board) {
        N = board.length;
        isPassed = new boolean[N][N];

        isPassed[0][0] = true;
        DFS(0, 0, -500,0, board);

        return minCost;
    }

    void DFS(int nowX, int nowY, int nowCost, int nowDir, int[][] board) {
        if (nowX == N-1 && nowY == N-1) {
            minCost = Math.min(minCost, nowCost);
            return;
        }
        if (nowCost > minCost) {
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = nowX + x_moves[i];
            int nextY = nowY + y_moves[i];
            if (inArea(nextX, nextY) && board[nextX][nextY] == 0 && !isPassed[nextX][nextY]) {
                isPassed[nextX][nextY] = true;
                if (nowDir != dir[i]) {
                    DFS(nextX, nextY, nowCost + 600, dir[i], board);
                }
                else {
                    DFS(nextX, nextY, nowCost + 100, dir[i], board);
                }
                isPassed[nextX][nextY] = false;
            }
        }
    }

    boolean inArea(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}