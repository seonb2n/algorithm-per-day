import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int houseNumber;
    static int[][] costs;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        houseNumber = Integer.parseInt(br.readLine());
        costs = new int[houseNumber + 1][3];
        result = new int[houseNumber + 1][3];


        for (int i = 0; i < houseNumber; i++) {
            costs[i + 1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 1; i <= houseNumber; i++) {
            for (int j = 0; j < 3; j++) {
                getMinCostIf(i,j);
            }
        }

        sb.append(getMinThreeNumber(result[houseNumber][0], result[houseNumber][1], result[houseNumber][2]));

        System.out.println(sb);
    }

    static int getMinTwoNumber(int a, int b) {
        return Math.min(a, b);
    }

    static int getMinThreeNumber(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    //n 호까지의 색깔은, (n-1,0) + n 1또는 2, (n-1,1) + n 0 또는 2, (n-1,2) + n 0 또는 1 중에 최솟값이다.
    static void getMinCostIf(int houseNumber, int nowHouseColor) {

        if (houseNumber > 1) {
            switch (nowHouseColor) {
                case 0:
                    result[houseNumber][0]
                            = getMinTwoNumber(result[houseNumber-1][1], result[houseNumber-1][2]) + costs[houseNumber][nowHouseColor];
                    break;
                case 1:
                    result[houseNumber][1]
                            = getMinTwoNumber(result[houseNumber-1][0], result[houseNumber-1][2]) + costs[houseNumber][nowHouseColor];
                    break;
                case 2:
                    result[houseNumber][2]
                            = getMinTwoNumber(result[houseNumber-1][0], result[houseNumber-1][1]) + costs[houseNumber][nowHouseColor];
                    break;
            }
        } else {
            //첫번째 집일 때
            switch (nowHouseColor) {
                case 0:
                    result[houseNumber][0]
                            = costs[houseNumber][nowHouseColor];
                    break;
                case 1:
                    result[houseNumber][1]
                            = costs[houseNumber][nowHouseColor];
                    break;
                case 2:
                    result[houseNumber][2]
                            = costs[houseNumber][nowHouseColor];
                    break;
            }
        }
    }
}
