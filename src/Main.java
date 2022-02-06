import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.sort;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N;
    static int[] caseArr;
    static int M;
    static int[] valueArr;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        caseArr = new int[N];
        caseArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());

        sort(caseArr);

        valueArr = new int[M];
        valueArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < M; i++) {
            isValueExist(valueArr[i], 0, N-1);
        }

        System.out.println(sb);
    }

    //값이 caseArr 안에 존재하면 1, 존재하지 않으면 0을 출력
    private static void isValueExist(int targetNumber, int startPoint, int endPoint) {
        if(startPoint == endPoint) {
            if(targetNumber != caseArr[startPoint]) {
                sb.append("0");
            } else {
                sb.append("1");
            }
            sb.append("\n");
            return;
        }
        int middlePoint = startPoint + (endPoint - startPoint) / 2;
        if(targetNumber == caseArr[middlePoint]) {
            sb.append("1");
            sb.append("\n");
        } else if (targetNumber > caseArr[middlePoint]){
            isValueExist(targetNumber, middlePoint+1, endPoint);
        } else if (targetNumber < caseArr[middlePoint]) {
            isValueExist(targetNumber, startPoint, middlePoint);
        }
    }
}
