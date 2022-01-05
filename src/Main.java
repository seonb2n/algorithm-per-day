import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int inputNumber = Integer.parseInt(word);

        int result = 0;
        while(result * (result + 1) * 3 + 2 < inputNumber) {
            result++;
        }

        if(result * (result + 1) * 3 + 2 == inputNumber) {
            System.out.println(result+2);
        } else {
            System.out.println(result+1);
        }

    }
}
