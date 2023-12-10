import java.util.*;

//https://leetcode.com/problems/valid-sudoku/
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowValid = new boolean[9][10];
        boolean[][] columnValid = new boolean[9][10];
        boolean[][] cellValid = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // i,j 를 바탕으로 해당 숫자가 몇번째 셀인지 알아야 함
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    if (rowValid[i][num] || columnValid[j][num] || cellValid[getCellNumber(i, j)][num]) {
                        return false;
                    }
                    rowValid[i][num] = true;
                    columnValid[j][num] = true;
                    cellValid[getCellNumber(i, j)][num] = true;
                }
            }
        }

        return true;
    }

    int getCellNumber(int i, int j) {
        if (i < 3 && j < 3) return 0;
        if (i < 3 && j < 6) return 1;
        if (i < 3 && j < 9) return 2;
        if (i < 6 && j < 3) return 3;
        if (i < 6 && j < 6) return 4;
        if (i < 6 && j < 9) return 5;
        if (i < 9 && j < 3) return 6;
        if (i < 9 && j < 6) return 7;
        if (i < 9 && j < 9) return 8;
        return 0;
    }
}