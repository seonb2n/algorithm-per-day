import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int[][] triangle;
    static int[][] maxResults;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int caseNumber = Integer.parseInt(br.readLine());
        triangle = new int[caseNumber+1][caseNumber+1];
        maxResults = new int[caseNumber+1][caseNumber+1];

        for (int i = 1; i <= caseNumber; i++) {
            triangle[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 1; i <= caseNumber; i++) {
            for (int j = 0; j < i; j++) {
                maxResults[i][j] = getMax(i, j);
            }
        }

        int max = 0;
        for (int i = 0; i < caseNumber; i++) {
            if(max < maxResults[caseNumber][i]) {
                max = maxResults[caseNumber][i];
            }
        }

        sb.append(max);

        System.out.println(sb);
    }

    private static int getMax(int i, int j) {
        //i 번째 줄의, j+1 번째 숫자까지의 최대값을 구하는 함수
        int result = 0;

        //첫째 줄이라면 자기 자신 값 return
        if(i == 1) {
            return triangle[i][j];
        } else {
            //그 이후일 때
            //첫 번째 수, 가운데 수, 마지막 수 나눠야 함
            //이전 줄
            int k = i-1;
            if(j == 0) {
                result = maxResults[k][0] + triangle[i][j];
            } else if (j == k) {
                result = maxResults[k][k-1] + triangle[i][j];
            } else {
                result = Math.max(maxResults[k][j-1], maxResults[k][j]) + triangle[i][j];
            }
        }

        return result;
    }

}
