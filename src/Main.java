import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        boolean[] booleans = new boolean[2000001];

        for (int i = 0; i < testCaseNumber; i++) {
            int number = Integer.parseInt(br.readLine());
                booleans[number+1000000] = true;
        }

        for (int i = 0; i < booleans.length; i++) {
            if (booleans[i]) {
                sb.append(i - 1000000 + "\n");
            }
        }

        System.out.println(sb);
    }
}
