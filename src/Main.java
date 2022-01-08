import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = Integer.parseInt(br.readLine());
        long[] nowPosition = new long[testCaseNumber];
        long[] endPosition = new long[testCaseNumber];

        for (int i = 0; i < testCaseNumber; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            nowPosition[i] = Long.parseLong(st.nextToken());
            endPosition[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 0; i < testCaseNumber; i++) {
            System.out.println(calculateResult(nowPosition[i], endPosition[i]));
        }
    }

    public static long calculateResult(long nowPosition, long endPosition) {
        long result;
        long distance = endPosition - nowPosition;

        if(distance == 1) {
            return 1;
        }

        if(distance == 2) {
            return 2;
        }

        result = calculateStartOddNumber(distance);

        return result;
    }

    public static long calculateStartOddNumber(long number) {
        double a = Math.sqrt(4 * number - 3) ;
        return (long)a;
    }
}
