import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb;
    static int coinType;
    static int total;
    static int[] coins;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        sb = new StringBuilder();

        coinType = Integer.parseInt(st.nextToken());
        total = Integer.parseInt(st.nextToken());
        coins = new int[coinType + 1];
        min = 0;

        for (int i = 1; i <= coinType; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int i = coinType; i >= 1; i--) {
            getCoinNumber(coins[i]);
        }

        System.out.println(min);
    }

    private static void getCoinNumber(int value) {
        //동전의 가치가 합보다 작다면
        if(value <= total) {
            int temp = total / value;
            min += temp;
            total = (total-(temp * value));
        }
    }
}

