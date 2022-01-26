import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static String[] plusGroup;
    static int[] calculatedGroup;
    static int min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        plusGroup = new String[51];
        calculatedGroup = new int[51];
        min = 0;

        //들어온 값을 - , + 로 쪼개야 한다.
        st = new StringTokenizer(br.readLine(), "-");
        int tokenNumber = st.countTokens();

        for (int i = 0; i < tokenNumber; i++) {
            plusGroup[i] = st.nextToken();
        }

        for (int i = 0; i < tokenNumber; i++) {
            int temp = 0;
            st = new StringTokenizer(plusGroup[i], "+");
            int number = st.countTokens();
            for (int j = 0; j < number; j++) {
                temp = temp + Integer.parseInt(st.nextToken());
            }
            calculatedGroup[i] = temp;
        }

        min = calculatedGroup[0];

        for (int i = 1; i < tokenNumber; i++) {
            min = min - calculatedGroup[i];
        }

        System.out.println(min);
    }
}

