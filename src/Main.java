import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.OptionalInt;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        word = word.toUpperCase();
        String[] letters = word.split("");

        int[] alphabets = new int[26];
        Arrays.fill(alphabets, 0);

        int maxCount = 0;

        for (int i = 0; i < letters.length; i++) {
            alphabets[letterToNumber(letters[i])]++;
        }

        //최대 값 찾는 알고리즘
        int max = 0;
        int maxIndex = 0;
        boolean isMaxOne = true;

        for (int i = 0; i < alphabets.length; i++) {
            if(alphabets[i] == max) {
                isMaxOne = true;
            }

            if(alphabets[i] > max) {
                max = alphabets[i];
                maxIndex = i;
                isMaxOne = false;
            }
        }

        if(isMaxOne) {
            System.out.println("?");
        } else {
            System.out.println(numberToLetter(maxIndex));
        }

    }

    private static int letterToNumber(String a) {
        return (int)a.charAt(0) - 65;
    }

    private static char numberToLetter(int a) {
        return (char)(a + 65);
    }
}
