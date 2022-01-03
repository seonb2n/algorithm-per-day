import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] words = br.readLine().split("");

        int[] results = new int[26];
        for (int i = 0; i < results.length; i++) {
            results[i] = -1;
        }

        for (int i = 0; i < words.length; i++) {
            int a = (int)words[i].charAt(0) - 97;
            if(results[a] == -1) {
                results[a] = i;
            }
        }

        for (int result : results) {
            System.out.print(result + " ");
        }

    }
}
