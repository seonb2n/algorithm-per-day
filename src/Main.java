import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int K;
    static int[] lanArr;
    static int N;
    static int max = 1;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lanArr = new int[K];
        for (int i = 0; i < K; i++) {
            lanArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(lanArr);

        sb.append(getMax(1, lanArr[K-1]));

        System.out.println(sb);

    }

    //startValue 와 endValue 사이에 있는 값 중에서 K를 만들 수 있는 값의 최댓값을 반환하는 함수
    private static int getMax(int startValue, int endValue) {
        int resultEndValue = 0;
        int resultMiddleValue = 0;
        int middleValue = (startValue + endValue) / 2;
        for (int i = 0; i < K; i++) {
            resultEndValue += lanArr[i] / endValue;
            resultMiddleValue += lanArr[i] / middleValue;
        }

        //가장 큰 수로 N 개를 만들 수 있으면 반환하면 된다.
        if(resultEndValue >= N) {
            return endValue;
        }

        //가장 큰 수로 N개를 만들 수 없다면, 범위를 더 작게 만들어야 한다.
        //가운데 수로 N 개를 만들 수 있는지 여부를 따져봐야 한다.
        //가운데 수로도 N 개를 만들 수 없다면, 범위를 더 작게 만들어야 한다.
        if(resultMiddleValue < N) {
            if((middleValue-1 != 0)) {
                max = Math.max(max, getMax(startValue, middleValue-1));
            } else {
                max = Math.max(max, getMax(startValue, middleValue));
            }
        }

        //가운데 수로는 N 개를 만들 수 있다면 끝에만 줄이면 된다.
        else {
            if((endValue - 1) != 0) {
                max = Math.max(max, getMax(middleValue, endValue-1));
            } else {
                max = Math.max(max, getMax(middleValue, endValue));
            }
        }

        return max;
    }
}
