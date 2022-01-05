import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int[] numbers = Arrays.stream(word.split(" ")).mapToInt(Integer::parseInt).toArray();

        int price = numbers[0];
        int fixedCost = numbers[1];
        int variableCost = numbers[2];

        int stockNumber;

        if(variableCost == fixedCost) {
            System.out.println(-1);
        } else {
            stockNumber = (int) Math.floor(price / (variableCost - fixedCost)) + 1;
            if(stockNumber > 0 ) {
                System.out.println(stockNumber);
            } else {
                System.out.println(-1);
            }
        }
    }
}
