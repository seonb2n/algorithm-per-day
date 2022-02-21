import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int N;
    static LinkedList<Integer> x;
    static int size = 0;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        x = new LinkedList<>();

        x.add(Integer.parseInt(br.readLine()));
        sb.append(x.get(0));
        sb.append("\n");
        size++;

        for (int i = 1; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            addNumber(temp);

            if(size % 2 == 0) {
                sb.append(x.get(size / 2 - 1));
            }
            else {
                sb.append(x.get((size-1) / 2));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void addNumber(int number) {
        //이분탐색 알고리즘으로 number 가 들어갈 위치를 찾는다.
        int min = 0;
        int max = size;
        int mid;

        while(min < max) {
            mid = (min + max) / 2;

            if(x.get(mid) < number) {
                min = mid+1;
            }
            else {
                max = mid;
            }
        }
        x.add(min, number);
        size++;
    }
}
