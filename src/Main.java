import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int N;
    static PriorityQueue<Number> x;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        x = new PriorityQueue<Number>();

        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());

            if(temp == 0) {
                if(x.isEmpty()) {
                    sb.append(0);
                    sb.append("\n");
                } else {
                    sb.append(x.poll().number);
                    sb.append("\n");
                }
            } else {
                Number n = new Number(temp);
                x.add(n);
            }

        }
        System.out.println(sb);
    }

    public static class Number implements Comparable<Number> {

        public Number(int n) {
            this.number = n;
        }

        int number;

        @Override
        public int compareTo(Number o) {
            int a = number;
            int b = o.number;

            if(number < 0) {
                a = a * (-1);
            }

            if(o.number < 0) {
                b = b * (-1);
            }

            //절대값이 동일한 경우에는 음수인 값을 왼쪽으로
            if(a == b) {
                return number > o.number ? 1 : -1;
            }

            //number 의 절대값을 비교해서, 오름차순 정렬
            return a > b ? 1 : -1;
        }
    }

}
