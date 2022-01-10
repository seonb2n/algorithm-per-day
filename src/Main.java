import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = new int[2];
        StringBuilder sb = new StringBuilder();

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //1부터 numbers[0] 까지의 자연수 중에서
        //numbers[1] 개로 구성된 수열을 출력

        List<Integer> values = new ArrayList<>();
        for (int i = 1; i <= numbers[0]; i++) {
            values.add(i);
        }

        String result = "";
        addNumber(values, sb, numbers[1], result);

        System.out.println(sb);

    }


    //숫자를 추가해주는 메서드
    public static void addNumber(List<Integer> numArr , StringBuilder sb, int numberCount, String result) {
        if (numberCount == 0) {
            sb.append(result);
            sb.append("\n");
        }

        if (numberCount != 0) {
            for (Integer integer : numArr) {
                result += integer + " ";
                List<Integer> numArr2 = new ArrayList<>(numArr);
                numArr2.remove(integer);
                addNumber(numArr2, sb, numberCount-1, result);
                result = result.substring(0, result.length()-2);
            }
        }
    }

}
