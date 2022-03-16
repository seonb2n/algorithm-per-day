import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int N;
    static int[] arr;
    static int[][] dp;
    static int questionNumber;
    static int startNumber;
    static int endNumber;

    public static void main(String[]args) throws IOException {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][N+1];

        for (int i = 1; i < N+1; i++) {
            Arrays.fill(dp[i], -1);
        }

        arr = new int[N];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::new).toArray();
        questionNumber = Integer.parseInt(br.readLine());
        for (int i = 0; i < questionNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            startNumber = Integer.parseInt(st.nextToken());
            endNumber = Integer.parseInt(st.nextToken());
            sb.append(isPalindrome(startNumber, endNumber));
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int isPalindrome(int startNumber, int endNumber) {
        if(startNumber > endNumber) {
            return 0;
        }

        if(dp[startNumber][endNumber] != -1) {
            return dp[startNumber][endNumber];
        }

        if(startNumber == endNumber) {
            dp[startNumber][endNumber] = 1;
            return 1;
        }

        if(endNumber - startNumber == 1) {
            if(arr[startNumber-1] == arr[endNumber-1]) {
                dp[startNumber][endNumber] = 1;
                return 1;
            }
        }

        if(isPalindrome(startNumber+1, endNumber-1) == 1 && arr[startNumber-1] == arr[endNumber-1]) {
            dp[startNumber][endNumber] = 1;
            return 1;
        }
        dp[startNumber][endNumber] = 0;
        return 0;
    }
}
