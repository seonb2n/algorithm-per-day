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
        //거리가 가질 수 있는 최소 범위
        min = 1;

        //거리가 가질 수 있는 최대 범위
        max = wifiArr[N-1] - wifiArr[0];

        if(C == 2) {
            return max;
        }

        //와이파이 사이의 길이를 비교하며 길이 이상일 때만 설치한다.
        while(min < max) {
            //설치된 개수가 C 의 개수인 길이 중 최대의 값을 찾는다.
            long mid = (max + min) / 2;

            int count = countNumber(mid);

            //count 가 C보다 많거나 같다면 mid 를 키우면 된다.
            if(count >= C) {
                //최솟값이 mid 이상일 수 있다.
                min = mid + 1;
                //min = mid+1 인 경우가 딱 최대일 수도 있다.
            }
            //count 가 C 보다 작다면 mid 가 너무 크다는 뜻
            else {
                max = mid;
            }
        }

        return min-1;
    }

    //주어진 길이에 대해서 몇개의 공유기가 설치 가능한지 구하는 함수
    private static int countNumber(long distance) {
        int count = 1;
        lastAddNumber = wifiArr[0];

        for (int i = 1; i < N; i++) {
            if((wifiArr[i] - lastAddNumber) >= distance) {
                count++;
                lastAddNumber = wifiArr[i];
            }

            if(count > C) {
                return count;
            }
        }

        return count;
    }
}
