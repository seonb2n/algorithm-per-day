import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static long[] treeArr;
    static int N;
    static long M;
    static long max = 0;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");

        treeArr = new long[N];
        for (int i = 0; i < N; i++) {
            long temp = Long.parseLong(st.nextToken());
            treeArr[i] = temp;
            if(max < temp) {
                max = temp;
            }
        }

        sb.append(findMaxHeight());

        System.out.println(sb);

    }

    //min 부터 max 까지의 값을 확인하면서 필요한 나무만큼 가져가면서 높이의 최댓값을 구해야 한다.
    private static long findMaxHeight() {
        long min = 0;
        long mid = 0;
        max++;

        while (min < max) {
            long count = 0;
            mid = (min + max) / 2;

            //절단기의 높이가 mid 일 때, 나무를 필요한 길이만큼 가져가는지 확인한다.
            for (int i = 0; i < N; i++) {
                if(mid < treeArr[i]) {
                    count += (treeArr[i] - mid);
                }
            }

            //자른 나무가 필요한 길이보다 짧다는 뜻은 높이가 높다는 뜻
            if(count < M) {
                max = mid-1;
            }
            //자른 나무가 필요한 길이보다 길다는 뜻은 높이가 높아질 수 있다는 뜻
            else {
                min = mid;
            }

            //차이가 1이면 max, min 의 값이 더 이상 바뀌지 않는다.
            if((max - min) == 1) {
                //max 일 때 count 를 세봐야 한다.
                count = 0;
                for (int i = 0; i < N; i++) {
                    if(max < treeArr[i]) {
                        count += (treeArr[i] - max);
                    }
                }
                //max 일 때 필요한 나무보다 count 가 크거나 같다면
                if(count >= M) {
                    return max;
                } else {
                    return min;
                }
            }
        }

        return min;
    }
}
