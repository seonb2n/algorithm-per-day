import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int testCaseNumber;
    static Arr[] arrArray;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        testCaseNumber = Integer.parseInt(br.readLine());

        arrArray = new Arr[testCaseNumber];
        dp = new int[testCaseNumber][testCaseNumber];

        for (int i = 0; i < testCaseNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arrArray[i] = new Arr(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        System.out.println(getMinCost(0, testCaseNumber-1));
    }

    //시작점부터 끝점에 있는 행렬에 대해서 곱셈의 최솟값을 반환하는 함수
    private static int getMinCost(int startPoint, int endPoint) {
        if(startPoint == endPoint) {
            return 0;
        }

        else if(endPoint-startPoint == 1) {
            if(dp[startPoint][endPoint] == 0) {
                dp[startPoint][endPoint] = arrArray[startPoint].i * arrArray[startPoint].j * arrArray[endPoint].j;
            }
            return dp[startPoint][endPoint];
        }

        else {
            if(dp[startPoint][endPoint] == 0) {
                dp[startPoint][endPoint] = Integer.MAX_VALUE;
                for (int i = startPoint; i < endPoint; i++) {
                    int tempCost = getMinCost(startPoint, i) + getMinCost(i+1, endPoint) + arrArray[startPoint].i * arrArray[i+1].i * arrArray[endPoint].j;
                    dp[startPoint][endPoint] = Math.min(dp[startPoint][endPoint], tempCost);
                }
            }
            return dp[startPoint][endPoint];
        }
    }

    public static class Arr {
        int i;
        int j;

        public Arr(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
