import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int[] caseNumber;
    static int[][] items;
    static Integer[][] dp;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        caseNumber = new int[2];

        caseNumber = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        items = new int[caseNumber[0]][2];
        dp = new Integer[caseNumber[1] + 1][caseNumber[0] + 1];

        for (int i = 0; i < caseNumber[0]; i++) {
            items[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //0부터 최대 무게 까지 확인하면서 dp 를 채워나간다.
        //dp 를 마지막까지 채웠을 때 최대 무게가 존재할 것이므로
        System.out.println(knapsack(caseNumber[1], caseNumber[0]-1));
    }

    //무게가 i이고, j번째 물건을 담을려고 할 떄 가장 가치가 높은 값을 반환하는 함수
    private static int knapsack(int i, int j) {
        if(j < 0)
            return 0;

        //아직 채워지지 않은 부분이라면
        if(dp[i][j] == null) {
            //값을 0 으로 초기화
            dp[i][j] = 0;
            //j 번째 물건이 들어갈 수 있다면
            if(i >= items[j][0]) {
                //j번째 물건을 넣기전의 가치와 j 번째 물건과, 무게 i - j 번째 아이템의 무게일 때의 최댓값 + 지금 아이템의 무게
                dp[i][j] = Math.max(knapsack(i, j-1), knapsack(i-items[j][0], j-1) + items[j][1]);
            }
            //j 번째 물건을 넣지 못하는 경우에는
            else {
                //이전에 넣었던 물건의 최대 가치를 갖는다.
                dp[i][j] = knapsack(i, j-1);
            }

        } else {
            return dp[i][j];
        }
        return dp[i][j];
    }
}

