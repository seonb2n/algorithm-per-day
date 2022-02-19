import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int N;
    static int M;
    static int[] numberList;
    static int[] resultList;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        numberList = new int[N];
        for (int i = 0; i < N; i++) {
            numberList[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        resultList = new int[M];
        for (int i = 0; i < M; i++) {
            resultList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numberList);

        for (int i = 0; i < M; i++) {
            findNumber(resultList[i]);
        }

        System.out.println(sb);
    }

    //주어진 숫자 i 에 대해서 개수를 찾아내는 알고리즘
    private static void findNumber(int n) {
        //lower bound
        int first = lowerBound(n);

        if(numberList[first] != n) {
            sb.append(0);
            sb.append(" ");
        } else {
            //upper bound
            int last = upperBound(n);
            sb.append(last - first);
            sb.append(" ");
        }
    }

    //원하는 값 이상이 처음 나오는 위치
    public static int lowerBound(int n) {
        int start = 0;
        int end = N-1;

        while (start < end) {
            int mid = (start + end) / 2;
            if(numberList[mid] < n) {
                start = mid+1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    //원하는 값을 초과한 값이 처음 나오는 위치
    public static int upperBound(int n) {
        int start = 0;
        int end = N-1;

        while (start < end) {
            int mid = (start + end) / 2;
            if(numberList[mid] <= n ) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if(numberList[N-1] == n) {
            return N;
        } else {
            return start;
        }
    }
}
