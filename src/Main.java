import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int caseNumber;
    static StringBuilder sb;
    static int[][] square;
    static int white;
    static int blue;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        caseNumber = Integer.parseInt(br.readLine());
        square = new int[caseNumber + 1][caseNumber + 1];

        for (int i = 0; i < caseNumber; i++) {
            square[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        divideAndConquer(caseNumber, 0, 0);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void divideAndConquer(int size, int x, int y) {
        //사각형의 크기와, 시작 위치를 나타내는 i 를 인자로 받는 함수

        //내부의 상태를 점검한다.
        boolean isBlue = false;
        boolean isWhite = false;

        for (int i = x; i < size + x; i++) {
            for (int j = y; j < size + y; j++) {
                if (square[i][j] == 1) {
                    isBlue = true;
                } else {
                    isWhite = true;
                }

                //내부에 흰색과 검은색이 섞여 있다면
                if (isBlue && isWhite) {
                    //4분할로 쪼갠다.
                    divideAndConquer(size / 2, x, y);
                    divideAndConquer(size / 2, x+size/2, y);
                    divideAndConquer(size / 2, x, y+size/2);
                    divideAndConquer(size / 2, x+size/2, y+size/2);
                    return;
                }

            }
        }

        if (isBlue) {
            blue++;
        } else if (isWhite) {
            white++;
        }
    }
}
