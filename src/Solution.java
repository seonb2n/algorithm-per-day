// https://leetcode.com/problems/maximal-square

class Solution {
    public int maximalSquare(char[][] matrix) {
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    int now = findMaxSquare(matrix, i, j, 2);
                    res = Math.max(res, now);
                }
            }
        }
        return res * res;
    }

    int findMaxSquare(char[][] matrix, int startRow, int startCol, int nowSize) {
        // 먼저 범위 체크
        if (startRow + nowSize > matrix.length || startCol + nowSize > matrix[0].length) {
            return nowSize - 1;
        }

        // 정사각형 영역 검사
        for (int i = startRow; i < startRow + nowSize; i++) {
            for (int j = startCol; j < startCol + nowSize; j++) {
                if (matrix[i][j] == '0') {
                    return nowSize - 1;
                }
            }
        }

        // 다음 크기의 정사각형 검사
        return findMaxSquare(matrix, startRow, startCol, nowSize + 1);
    }
}
