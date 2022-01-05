import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int totalNumber = Integer.parseInt(br.readLine());

        int[] wordNumber = new int[totalNumber];
        String[] words = new String[totalNumber];

        for (int i = 0; i < totalNumber; i++) {
            String read = br.readLine();
            int blank = read.indexOf(" ");
            wordNumber[i] = Integer.parseInt(read.substring(0, blank));
            words[i] = read.substring(blank+1);
        }

        for (int i = 0; i < totalNumber; i++) {
            printNumber(wordNumber, words, i);
            System.out.println();
        }
    }

    private static void printNumber(int[] wordNumber, String[] words, int i) {
        for (int j = 0; j < words[i].length(); j++) {
            String[] temp = new String[words[i].length()];
            temp = words[i].split("");
            for (int k = 0; k < wordNumber[i]; k++) {
                System.out.print(temp[j]);
            }
        }
    }
}
