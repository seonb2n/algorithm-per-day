import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNumber = Integer.parseInt(br.readLine());

        int count = 0;

        for (int i = 1; i <= caseNumber; i++) {
            if(isNumber(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean isNumber(int a) {
        if(a < 100) {
            return true;
        }

        int[] numbers = Arrays.stream((String.valueOf(a).split(""))).mapToInt(Integer::parseInt).toArray();
        int dif = numbers[1] - numbers[0];
        boolean result = false;
        for (int i = 1; i < numbers.length-1; i++) {
            result = (dif == numbers[i + 1] - numbers[i]);
        }

        return result;
    }

}
