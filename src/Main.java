import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    static StringBuilder sb;
    static int[][] lines;
    static int caseNumber;
    static int[] dp;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        caseNumber = Integer.parseInt(br.readLine());

        lines = new int[caseNumber][2];
        dp = new int[caseNumber];
        max = 0;

        for (int i = 0; i < caseNumber; i++) {
            lines[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //정렬 필요
        Arrays.sort(lines, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < caseNumber; i++) {
            //1개는 최소한 연결 가능하기 때문
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                //자기 이전의 전선을 확인

                //B 전봇대에 연결된 점이 더 위에 있다면 서로 겹치지 않는다.
                if(lines[i][1] > lines[j][1]) {
                    //이전에 탐색한 j 에서 출발한 점에다 1을 더한 값이 된다.
                    //조건에 부합하는 갑 중 최댓값이 dp[i] 가 된다.
                    if(dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
        }

        //최대로 많이 연결된 경우를 찾는다.
        for (int i = 0; i <caseNumber; i++) {
            if(dp[i] > max) {
                max = dp[i];
            }
        }

        System.out.println(caseNumber - max);

    }
}

