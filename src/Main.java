import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] numbers;
    static StringBuilder sb;
    static int[] numberList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        numberList = new int[numbers[1]];

        dfs(1, 0);
        System.out.println(sb);

    }

    public static void dfs(int startNumber, int numberCount) {

        if (numberCount == numbers[1]) {
            //숫자의 개수가 numbers[1]개와 동일하다면
            //앞서 있던 모든 조건을 만족한다는 뜻이므로 답을 출력
            for (int i : numberList) {
                sb.append(i);
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        //현재 상황 전까지는 조건대로 되어 있고,
        // 현재 상황인 numberCount 에 숫자를 추가하는 상황
        for (int i = startNumber; i <= numbers[0]; i++) {
            //전의 경우보다 큰 숫자부터 탐색하며 된다.
            numberList[numberCount] = i;
            dfs(i + 1, numberCount + 1);
        }
    }
}
