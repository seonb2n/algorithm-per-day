import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int[][] problems = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        //주어진 입력에 대해서 이중 배열로 처리
        for (int i = 0; i < 9; i++) {
            problems[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //배열 내의 각각의 0 에 대해서 값을 탐색
        backTracking(0, 0);

        System.out.print(sb);
    }

    private static void backTracking(int row, int col) {

        //해당 row의 행이 다 채워진 경우는 다음 row로
        if(col == 9) {
            backTracking(row+1, 0);
            //해당 함수가 끝났다는 것은, 모든 줄이 다 채워진 것이므로 return
            return;
        }

        //행과 줄이 모두 채워졌기 때문에 출력 후 종료
        if(row == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(problems[i][j]);
                    sb.append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            //하나의 경우만 출력하므로 시스템 종료
            System.exit(0);
        }

        //해당 위치의 값이 0이라면 가능한 값 탐색
        if(problems[row][col] == 0) {
            boolean[] impossibleNumber = new boolean[9];
            verticalImPossible(col, impossibleNumber);
            horizontalImPossible(row, impossibleNumber);
            rectangleImPossible(col, row, impossibleNumber);
            //값이 false 로 남아있는 경우만 가능한 값이다.
            for (int i = 0; i < 9; i++) {
                if(!impossibleNumber[i]) {
                    problems[row][col] = i+1;
                    //해당 줄에 다음 0을 탐색한다.
                    backTracking(row, col+1);
                }
            }
            //해당하는 합당한 값이 없으면 해당 값을 0 으로 비워두고 다시 이전 노드로 돌아간다.
            problems[row][col] = 0;
            return;
        }

        //이전까지 알맞은 값이 들어가있으면 다음 줄로 넘어간다.
        backTracking(row, col+1);
    }



    private static void rectangleImPossible(int columnNumber, int rowNumber, boolean[] impossibleNumbers) {
        int rectangleNumber = getRectangleNumber(columnNumber, rowNumber);

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                if (problems[j + ((rectangleNumber / 3) * 3)][k + ((rectangleNumber % 3) * 3)] != 0) {
                    impossibleNumbers[problems[j + ((rectangleNumber / 3) * 3)][k + ((rectangleNumber % 3) * 3)] - 1] = true;
                }
            }
        }
    }

    private static int getRectangleNumber(int columnNumber, int rowNumber) {
        if(rowNumber < 3) {
            if(columnNumber < 3) {
                return 0;
            } else if(columnNumber < 6) {
                return 1;
            } else if(columnNumber < 9) {
                return 2;
            }
        } else if(rowNumber < 6) {
            if(columnNumber < 3) {
                return 3;
            } else if(columnNumber < 6) {
                return 4;
            } else if(columnNumber < 9) {
                return 5;
            }
        } else if(rowNumber < 9) {
            if(columnNumber < 3) {
                return 6;
            } else if(columnNumber < 6) {
                return 7;
            } else if(columnNumber < 9) {
                return 8;
            }
        }

        return 0;
    }

    private static void horizontalImPossible(int rowNumber, boolean[] impossibleNumbers) {
        for (int j = 0; j < 9; j++) {
            if (problems[rowNumber][j] != 0) {
                impossibleNumbers[problems[rowNumber][j] - 1] = true;
            }
        }
    }

    private static void verticalImPossible(int columnNumber, boolean[] impossibleNumbers) {
        for (int j = 0; j < 9; j++) {
            if(problems[j][columnNumber] != 0) {
                impossibleNumbers[problems[j][columnNumber] - 1] = true;
            }
        }
    }

}
