import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int houseNumber;
    static int[][] costs;
    static int[] chosenColor;
    static int[] chosenCosts;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        houseNumber = Integer.parseInt(br.readLine());
        costs = new int[houseNumber+1][3];
        chosenColor = new int[houseNumber+1];
        chosenColor[0] = -999;
        chosenCosts = new int[houseNumber+1];
        chosenCosts[0] = -999;
        result = new int[houseNumber+1];


        for (int i = 0; i < houseNumber; i++) {
            costs[i+1] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        sb.append(getMinCost(houseNumber));

        System.out.println(sb);
    }

    //어떤 호수의 집 까지의 최소값은 n-2 번째 집까지의 최소값 + n-1, n 호의 최소값
    private static int getMinCost(int nowHouse) {
        //집의 개수가 3개 이상인 경우
        if (nowHouse > 2) {
            result[nowHouse] = getMinCost(nowHouse-2) + getMinCostOf(nowHouse - 1, nowHouse);
            return result[nowHouse];
        } else if(nowHouse == 2){
            int min = getMinCostOf(1, 2);
            result[2] = min;
            return result[2];
        } else {
            int min = 1001;
            for (int i = 0; i < 3; i++) {
                if(costs[1][i] < min) {
                    min = costs[1][i];
                    chosenColor[1] = i;
                }
            }
            result[1] = min;
            return min;
        }
    }

    //이전까지의 results 에다가 두개의 집을 더했을 때 최소값을 추가하는 메서드
    private static int getMinCostOf(int lastBeforeHouse, int lastHouse) {
        int min = 3001;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i != j && i != chosenColor[lastBeforeHouse-1]) {
                    if (min >= (costs[lastBeforeHouse][i] + costs[lastHouse][j])) {
                        min = costs[lastBeforeHouse][i] + costs[lastHouse][j];
                        chosenColor[lastBeforeHouse] = i;
                        chosenColor[lastHouse] = j;
                    }
                }
            }
        }
        return min;
    }
}
