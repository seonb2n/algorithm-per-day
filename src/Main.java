import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int[] numbers;
    static int[] symbols;

    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int numberCount = Integer.parseInt(br.readLine());

        numbers = new int[numberCount];
        symbols = new int[4];
        max = -999999999;
        min = 999999999;

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        symbols = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        backTracking(0, 0);

        sb.append(max);
        sb.append("\n");
        sb.append(min);
        System.out.print(sb);
    }

    private static void backTracking(int numberCount, int result) {
        if (numberCount == numbers.length) {
            //마지막 숫자까지 연산을 끝냈다면 값을 반환
            if (max < result) {
                max = result;
            }

            if (min > result) {
                min = result;
            }
            //계산 결과 값이 최댓값 혹은 최솟값인지 검증 필요
            return;
        }
        if (numberCount == 0) {
            result = numbers[0];
            numberCount++;
        }

        //for 문으로 4개 중에 하나를 택함
        for (int i = 0; i < 4; i++) {
            //4개 중에 0이 아닌 첫번째 값을 택함
            if (symbols[i] != 0) {
                //해당 symbol 로 계산하고 그 결과와 함께 다음 함수 호출
                int result2 = calculate(i, numbers[numberCount], result);
                //해당 symbol 은 사용됐으므로 값 -
                symbols[i]--;
                backTracking(numberCount + 1, result2);
                //해당 symbol 을 사용한 줄기는 끝났으므로 다시 값 +
                symbols[i]++;
            }
        }

    }

    private static int calculate(int symbol, int number, int result) {
        switch (symbol) {
            case 0:
                result = result + number;
                break;
            case 1:
                result = result - number;
                break;
            case 2:
                result = result * number;
                break;
            case 3:
                result = result / number;
                break;
        }
        return result;
    }
}
