import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N;
    static long K;
    static long minCount;
    static long maxCount;
    static long possibleMinMid;
    static long possibleMaxMid;
    static long possibleValue;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        K = Long.parseLong(br.readLine());

        sb.append(getKNumber());

        System.out.println(sb);
    }

    //K 가 존재할 수 있는 범위를 탐색해나가는 함수
    static long getKNumber() {
        if(K == 1) {
            return 1;
        }
        else if(K == N * N) {
            return N * N;
        }

        long min;
        long max;
        min = 1;
        max = N * N;

        while (min < max) {
            //숫자가 mid 일 때, 해당 숫자가 몇 번 째로 큰 숫자인지를 알아내야 한다.
            long mid = (min + max) / 2;
            getK(mid);

            //mid 를 값으로 하는 숫자의 경우가 처음 등장하는 것보다 K가 앞에 있다면
            if(K <= minCount) {
                //mid 보다 작은 가장 큰 수
                max = possibleMaxMid;
            }
            //mid 를 값으로 하는 숫자의 최대 범위보다 K 가 더 크다면
            else if(maxCount < K){
                //mid 보다 큰 가장 작은 수
                min = possibleMinMid;
            }
            //mid 를 값으로 할 때, 숫자의 범위가 해당 범위 내에 존재한다면
            else {
                //가능한 범위 내의 값을 전달
                return possibleValue;
            }
        }

        return min;

    }

    //주어진 number 에 대해서 number 가 몇 번째 숫자인지 찾아야 한다.
    static void getK(long number) {
        minCount = 0;
        maxCount = 0;
        //number 보다 작은 수 중 가장 큰 수
        possibleMaxMid = 0;
        //number 보다 큰 수 중 가장 작은 수
        possibleMinMid = 999999999;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                long temp = i * j;
                if(temp < number) {
                    minCount++;
                    maxCount++;
                    if(possibleMaxMid < temp) {
                        possibleMaxMid = temp;
                        possibleValue = temp;
                    }
                } else if(temp == number) {
                    maxCount++;
                    possibleValue = temp;
                } else {
                    if(temp < possibleMinMid) {
                        possibleMinMid = temp;
                    }
                    break;
                }
            }
        }
    }
}
