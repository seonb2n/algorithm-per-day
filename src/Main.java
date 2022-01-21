import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb;
    static char[] a;
    static char[] b;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        dp = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(LCS(a.length - 1, b.length - 1));
    }

    static int LCS(int x, int y) {
        //범위 밖일 경우 0을 반환
        if( x == -1 || y == -1) {
            return 0;
        }

        //아직 탐색하지 않은 경우라면
        if(dp[x][y] == -1) {
            //문자가 같다면
            if(a[x] == b[y]) {
                dp[x][y] = LCS(x - 1, y - 1) + 1;
            } else {
                //같지 않다면
                dp[x][y] = Math.max(LCS(x - 1, y), LCS(x, y-1));
            }
        }
        return dp[x][y];
    }
}

