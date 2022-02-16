import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N;
    static int[] A;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");

        A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getLCS());

    }

    //해당 범위 내 증가하는 수열의 길이를 반환하는 함수
    public static int getLCS() {
        int[] result;
        result = new int[N];
        int lastAddPoint = 0;

        result[0] = A[0];

        for (int i = 1; i < N; i++) {
            //만약 수열에 추가하고자 하는 수가 result 의 마지막 수보다 크다면 그대로 값을 추가해준다.
            if(result[lastAddPoint] < A[i]) {
                lastAddPoint++;
                result[lastAddPoint] = A[i];
            }

            //수열에 추가하고자 하는 수가 마지막 수보다 크지 않다면, 새로운 수가 들어갈 적절한 위치를 찾아줘야 한다.
            else {
                //새로운 수는 자기보다 작은 result 의 수 바로 앞의 수를 대체한다.
                //upper Bound 로 추가하고자 하는 수를 초과한 값이 처음 나오는 위치를 찾아야 한다.
                int min = 0;
                int max = lastAddPoint;
                int temp = A[i];
                while(min < max) {

                    int mid = (min + max) / 2;
                    if(result[mid] < temp) {
                        min = mid+1;
                    } else {
                        max = Math.max(0, mid);
                    }

                }
                result[min] = temp;
            }
        }

        return lastAddPoint+1;
    }
}
