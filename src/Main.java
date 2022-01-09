import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] numbers = new int[10001];

        for (int i = 0; i < testCaseNumber; i++) {
            int result = Integer.parseInt(br.readLine());
            numbers[result]++;
        }

        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i] != 0) {
                for (int k = 0; k < numbers[i]; k++) {
                    sb.append(i + "\n");
                }
            }
        }

        System.out.println(sb);
    }

}
