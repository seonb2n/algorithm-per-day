import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int inputNumber = Integer.parseInt(word);

        int n = 1;
        int x = 0;
        int y = 0;
        int distance = 0;

        while(calcaulateStartN(n) <= inputNumber) {
            n++;
        }

        n = n - 1;

        if(n % 2 == 0) {
            x = n - 1;
            y = 0;
            distance = inputNumber - calcaulateStartN(n);
            x = x-distance;
            y = y + distance;
        } else {
            x = 0;
            y = n -1;
            distance = inputNumber - calcaulateStartN(n);
            x = x + distance;
            y = y - distance;
        }

        System.out.println((y+1) + "/" + (x+1));


    }

    public static int calcaulateStartN(int n) {
        return (n * n - n + 2) / 2;
    }
}
