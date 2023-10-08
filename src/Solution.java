import java.util.*;

//https://leetcode.com/problems/maximal-square/
class Solution {

    static int[] x_moves = {-1, 0, 1, 0};
    static int[] y_moves = {0, 1, 0, -1};

    public int maximalSquare(char[][] matrix) {

        int maxSize = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int nowSize = maxSize + 1;
                    while (true) {
                        if (isSquare(matrix, i, j, nowSize)) {
                            nowSize++;
                        }
                        else {
                            break;
                        }
                    }
                    maxSize = Math.max(maxSize, nowSize-1);
                }
            }
        }

        return (maxSize) * (maxSize);
    }

    boolean isSquare(char[][] matrix, int x, int y, int size) {
        boolean result = true;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!inArea(x+i, y+j, matrix.length, matrix[0].length) || matrix[x+i][y+j] == '0') {
                    return false;
                }
            }
        }

        return result;
    }

    boolean inArea(int x, int y, int N, int M) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

}