import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int size;
    static StringBuilder sb;
    static int[][] square;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        size = Integer.parseInt(br.readLine());
        square = new int[size][size];

        for (int i = 0; i < size; i++) {
            square[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        quadTree(size, 0, 0);

        System.out.println(sb);
    }

    private static void quadTree(int size, int startX, int startY) {
        //해당 size 크기의 사각형이 0또는 1로만 이루어졌는지 확인
        boolean isOne = false;
        boolean isZero = false;

        for (int i = startY; i < size+startY; i++) {
            for (int j = startX; j < size + startX; j++) {
                if(square[i][j] == 0) {
                    isZero = true;
                } else {
                    isOne = true;
                }

                if(isOne && isZero) {
                    sb.append("(");
                    quadTree(size/2, startX, startY);
                    quadTree(size/2, startX + size/2, startY);
                    quadTree(size/2, startX, startY + size/2);
                    quadTree(size/2, startX + size/2, startY + size/2);
                    sb.append(")");
                    return;
                }
            }
        }

        if(isOne) {
            sb.append(1);
        } else if(isZero) {
            sb.append(0);
        }

    }
}
