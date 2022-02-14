import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static long[] wifiArr;
    static long lastAddNumber;
    static int N;
    static int C;
    static long max;
    static long min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        wifiArr = new long[N];

        for (int i = 0; i < N; i++) {
            wifiArr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(wifiArr);

        sb.append(findMaxLength());

        System.out.println(sb);
    }

    //min 부터 max 사이의 범위 중에 C개의 공유기를 설치할 때, 공유기 사이의 범위가 최대가 되도록 해야 한다.
    private static long findMaxLength() {
        min = wifiArr[0];
        max = wifiArr[N-1];
        long maxMinGap = max - min;

        if(C == 2) {
            return maxMinGap;
        }

        //와이파이 사이의 길이를 비교하며 길이 이상일 때만 설치한다.
        long mid = 1;
        while(mid < maxMinGap) {
            //설치된 개수가 C 의 개수인 길이 중 최대의 값을 찾는다.
            lastAddNumber = wifiArr[0];
            mid = (max + min) / 2;
            int count = 1;
            for (int i = 1; i < N; i++) {
                if((wifiArr[i] - lastAddNumber) >= mid) {
                    count++;
                    lastAddNumber = wifiArr[i];
                }

                if(count > C) {
                    break;
                }
            }

            //count 가 C보다 많다면 mid 가 너무 작다는 뜻
            if(count > C) {
                min = mid + 1;
            }
            //count 가 C 보다 작다면 mid 가 너무 크다는 뜻
            else if (count < C){
                max = mid - 1;
            }

            //count 가 C 와 같다면 max 를 검증해야 한다.
            else {
                lastAddNumber = wifiArr[0];
                count = 1;
                for (int i = 1; i < N; i++) {
                    if((wifiArr[i] - lastAddNumber) >= max) {
                        count++;
                        lastAddNumber = wifiArr[i];
                    }
                }

                if(count == C) {
                    return max;
                } else {
                    max--;
                    min = mid;
                }
            }
        }

        return max;
    }
}
