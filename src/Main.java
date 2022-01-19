import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int[] numbers;
    static int[] results;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int caseNumber = Integer.parseInt(br.readLine());

        numbers = new int[caseNumber+1];
        results = new int[caseNumber+1];

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < caseNumber; i++) {
            results[i] = getNumber(i);
            if(max < results[i]) {
                max = results[i];
            }
        }

        System.out.println(max);
    }

    private static int getNumber(int i) {
        if(i == 0) {
            return 1;
        } else {
            int minMax = 0;
            boolean valueChanged = false;
            for (int j = 0; j < i; j++) {
                //j 의 숫자가 i보다 작다면
                if (numbers[j] < numbers[i]) {
                    //그 중에 results[j] 값이 가장 큰 놈을 찾는다.
                    if (minMax < results[j]) {
                        minMax = results[j];
                        valueChanged = true;
                    }
                }
            }
            //i 보다 작은 최댓값이 없는 경우에는 1을 반환한다.
            if (!valueChanged) {
                return 1;
            }

            //i 보다 작은 최댓값의 result + 1을 반환한다.
            return minMax + 1;
        }
    }
}
