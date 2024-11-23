import java.util.*;

// leetcode sudoku-solver
class Solution {
    private int[] rows;    // rows[i]의 각 비트는 i번 행에서 해당 숫자의 사용 여부
    private int[] cols;    // cols[j]의 각 비트는 j번 열에서 해당 숫자의 사용 여부
    private int[] boxes;   // boxes[box]의 각 비트는 box번 박스에서 해당 숫자의 사용 여부

    public void solveSudoku(char[][] board) {
        rows = new int[9];
        cols = new int[9];
        boxes = new int[9];

        // 현재 보드 상태 초기화
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int box = (i / 3) * 3 + j / 3;
                    updateState(i, j, box, num, true);
                }
            }
        }

        solve(board);
    }

    private boolean solve(char[][] board) {
        Cell nextCell = findBestCell(board);
        if (nextCell == null) {
            return true;
        }

        int row = nextCell.row, col = nextCell.col;
        int box = (row / 3) * 3 + col / 3;

        // 1부터 9까지 숫자를 시도
        for (char num = '1'; num <= '9'; num++) {
            int n = num - '0';
            if (canPlace(row, col, box, n)) {
                // 가능하다면 숫자를 놓고
                board[row][col] = num;
                updateState(row, col, box, n, true);

                // 다음 빈 칸으로 재귀 호출
                if (solve(board)) {
                    return true;
                }

                // 실패하면 백트래킹
                board[row][col] = '.';
                updateState(row, col, box, n, false);
            }
        }

        return false;
    }

    private Cell findBestCell(char[][] board) {
        Cell bestCell = null;
        int minPossibilities = 10;

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    int box = (row / 3) * 3 + col / 3;
                    int count = countPossibleNumbers(row, col, box);

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

    private int countPossibleNumbers(int row, int col, int box) {
        int count = 0;
        int mask = ~(rows[row] | cols[col] | boxes[box]);

        // 1부터 9까지의 비트만 확인
        mask &= 0x3FE;  // 0011 1111 1110

        // 비트 1의 개수를 세기
        while (mask != 0) {
            count++;
            mask &= (mask - 1);  // 최하위 비트 제거
        }

        return count;
    }

    private boolean canPlace(int row, int col, int box, int num) {
        // num 비트가 모두 0이어야 배치 가능
        return ((rows[row] | cols[col] | boxes[box]) & (1 << num)) == 0;
    }

    private void updateState(int row, int col, int box, int num, boolean place) {
        int mask = 1 << num;
        if (place) {
            rows[row] |= mask;
            cols[col] |= mask;
            boxes[box] |= mask;
        } else {
            mask = ~mask;
            rows[row] &= mask;
            cols[col] &= mask;
            boxes[box] &= mask;
        }
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
