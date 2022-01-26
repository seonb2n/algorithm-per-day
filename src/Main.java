import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int caseNumber;
    static long[] oilPrices;
    static long[] roadPrices;
    static long lowOilPrice;
    static int lowOilPoint;
    static long min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        caseNumber = Integer.parseInt(br.readLine());

        oilPrices = new long[caseNumber+1];
        roadPrices = new long[caseNumber+1];

        roadPrices = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        oilPrices = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        min = 0;
        greedy();

        System.out.println(min);
    }

    private static void greedy() {
        //만약 i 번째 지점의 기름값 < j번째 지점의 기름값
        //i부터 j 까지는 i 기름으로 간다.
        lowOilPrice = oilPrices[0];
        lowOilPoint = 0;

        for (int i = 1; i < caseNumber; i++) {
            if(oilPrices[i] <= lowOilPrice) {
                //지금까지의 거리는 lowOilPrice 로 달린다.
                for (int j = lowOilPoint; j < i; j++) {
                    min += roadPrices[j] * lowOilPrice;
                }
                lowOilPrice = oilPrices[i];
                lowOilPoint = i;
            }
        }

        //싼 가격이 중간에 머물고 있으면
        //싼 가격부터 마지막까지를 모두 싼 가격으로
        if(lowOilPoint < caseNumber - 1) {
            for (int j = lowOilPoint; j < caseNumber-1; j++) {
                min += roadPrices[j] * lowOilPrice;
            }
        }
    }
}

