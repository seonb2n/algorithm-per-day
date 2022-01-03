import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNumber = Integer.parseInt(br.readLine());
        String[] caseStrings = new String[caseNumber];

        for(int i=0; i<caseStrings.length; i++) {
            int[] studentNumber = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int result = 0;
            int count = 0;
            for(int k = 0; k < studentNumber[0]; k++) {
                result += studentNumber[k+1];
            }
            int average = result / (studentNumber.length-1);
            for(int k = 0; k < studentNumber[0]; k++) {
                count = (studentNumber[k+1] > average) ? ++count : count;
            }

            caseStrings[i] = String.format("%.3f", ((float)count / studentNumber[0]) * 100);
        }

        for (String caseString : caseStrings) {
            System.out.println(caseString + "%");
        }

    }
}
