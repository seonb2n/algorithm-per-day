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
    static long numberCount;

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

        long max = ((long) N) * N;

        if(K == 1) {
            return 1;
        }
        else if(K == max) {
            return max;
        }

        long min = 1;

        while (min < max) {

            long mid = (min+max) / 2;
            long count = getK(mid);

            if(count >= K) {
                max = mid;
            }

            else {
                min = mid + 1;
            }

        }

        return min;
    }

    //i, j에 위치한 숫자에 대해서 해당 숫자가 몇 번째 숫자인지를 구해야 한다.
    static long getK(long temp) {
        numberCount = 0;
        numberCount += Math.min(N, temp);

        for (int k = 2; k <= N; k++) {
            numberCount+= Math.min(temp / k, N);
        }

        return numberCount;
    }
}
