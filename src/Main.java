import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int T;
    static int testCaseNumber;
    static int[] fileSize;
    static int[][] dp;
    static int[] sum;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            testCaseNumber = Integer.parseInt(br.readLine());
            fileSize = new int[testCaseNumber];
            dp = new int[testCaseNumber][testCaseNumber];
            sum = new int[testCaseNumber];
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < testCaseNumber; j++) {
                fileSize[j] = Integer.parseInt(st.nextToken());
                if(j == 0) {
                    sum[0] = fileSize[0];
                }
                else {
                    sum[j] = sum[j-1] + fileSize[j];
                    dp[j-1][j] = fileSize[j-1] + fileSize[j];
                }
            }
            sb.append(getDp(0, testCaseNumber-1)).append("\n");
         }
        System.out.println(sb);
    }

    //startPoint 부터 endPoint 까지 파일을 합치는 비용의 최소 비용을 구하는 함수
    public static int getDp(int startPoint, int endPoint) {
        int gap = endPoint - startPoint;
        if(gap == 0) {
            return dp[startPoint][endPoint];
        }
        if(gap == 1) {
            return dp[startPoint][endPoint];
        }

        dp[startPoint][endPoint] = Integer.MAX_VALUE;
        for (int i = startPoint; i < endPoint; i++) {
            int tempSum = getDp(startPoint, i) + getDp(i+1, endPoint) + getSum(startPoint, endPoint);
            dp[startPoint][endPoint] = Math.min(dp[startPoint][endPoint], tempSum);
        }
        return dp[startPoint][endPoint];

    }

    public static int getSum(int startPoint, int endPoint) {
        if(startPoint == 0) {
            return sum[endPoint];
        }

        else {
            return sum[endPoint] - sum[startPoint-1];
        }
    }
}
