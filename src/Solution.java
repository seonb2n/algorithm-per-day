import java.util.*;

// leetcode sudoku-solver
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        Cell nextCell = findBestCell(board);

        if (nextCell == null) {
            return true;
        }

        // board를 순회하면서 빈 칸 찾기
        // 빈 칸을 찾으면
        // 1부터 9까지 숫자를 시도
        for (char num = '1'; num <= '9'; num++) {
            // 현재 위치에 num을 놓을 수 있는지 검사
            if (isValid(board, nextCell.row, nextCell.col, num)) {
                // 가능하다면 숫자를 놓고
                board[nextCell.row][nextCell.col] = num;

                // 다음 빈 칸으로 재귀 호출
                if (solve(board)) {
                    return true;
                } else {
                    // 실패하면 백트래킹
                    board[nextCell.row][nextCell.col] = '.';
                }
            }
        }
        // 1-9 중 어떤 숫자도 놓을 수 없다면 실패
        return false;
    }

    private Cell findBestCell(char[][] board) {
        Cell bestCell = null;
        int minPossibilities = 10; // 9보다 큰 값으로 초기화

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    int count = countPossibleNumbers(board, row, col);

                    // 가능한 숫자가 더 적은 셀을 찾았다면 업데이트
                    if (count < minPossibilities) {
                        minPossibilities = count;
                        bestCell = new Cell(row, col, count);

                        // 최적화: 가능한 숫자가 1개뿐인 셀을 찾았다면 즉시 반환
                        if (count == 1) {
                            return bestCell;
                        }
                    }
                }
            }
        }

        return bestCell;
    }

    private int countPossibleNumbers(char[][] board, int row, int col) {
        int count = 0;
        for (char num = '1'; num <= '9'; num++) {
            if (isValid(board, row, col, num)) {
                count++;
            }
        }
        return count;
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

class Cell {
    int row;
    int col;
    int possibleCount;

    Cell(int row, int col, int possibleCount) {
        this.row = row;
        this.col = col;
        this.possibleCount = possibleCount;
    }
}
