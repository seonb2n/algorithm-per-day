import java.util.*;

// leetcode sudoku-solver
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        // board를 순회하면서 빈 칸 찾기
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // 빈 칸을 찾으면
                if (board[row][col] == '.') {
                    // 1부터 9까지 숫자를 시도
                    for (char num = '1'; num <= '9'; num++) {
                        // 현재 위치에 num을 놓을 수 있는지 검사
                        if (isValid(board, row, col, num)) {
                            // 가능하다면 숫자를 놓고
                            board[row][col] = num;

                            // 다음 빈 칸으로 재귀 호출
                            if (solve(board)) {
                                return true;
                            } else {
                                // 실패하면 백트래킹
                                board[row][col] = '.';
                            }
                        }
                    }
                    // 1-9 중 어떤 숫자도 놓을 수 없다면 실패
                    return false;
                }
            }
        }
        // 모든 칸이 채워졌다면 성공
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        // 가로줄 검사
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // 세로줄 검사
        for (int x = 0; x < 9; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // 3x3 박스 검사
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
