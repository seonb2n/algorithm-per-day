import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int caseNumber;
    static StringBuilder sb;
    static Deque<Integer> arr;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        caseNumber = Integer.parseInt(br.readLine());
        arr = new LinkedList<>();

        for (int i = 0; i < caseNumber; i++) {
            //첫째 줄에는 실행할 함수의 명령어 모음이 들어있다.
            char[] pOrders = br.readLine().toCharArray();

            //둘째 줄에는 배열의 크기가 들어있다.
            int sizeOfArray = Integer.parseInt(br.readLine());

            //셋째 줄에는 배열이 들어있다.
            String temp = br.readLine();
            temp = temp.substring(1, temp.length()-1);
            st = new StringTokenizer(temp, ",");
            for (int s = 0; s < sizeOfArray; s++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }

            //명령에 따라 배열을 처리하고, 결과를 sb에 추가한다.
            doFunction(pOrders);
            arr.clear();
        }

        System.out.println(sb);
    }

    private static void doFunction(char[] pOrders) {
        int cursor = 0;

        for (int i = 0; i < pOrders.length; i++) {
            switch (pOrders[i]) {
                case 'R':
                    if (cursor == 0) {
                        cursor = arr.size() - 1;
                    } else {
                        cursor = 0;
                    }
                    break;
                case 'D':
                    if (arr.size() == 0) {
                        sb.append("error");
                        sb.append("\n");
                        return;
                    } else {
                        if (cursor != 0) {
                            arr.removeLast();
                        } else {
                            arr.removeFirst();
                        }
                        break;
                    }
            }
        }

        if(arr.size() == 0) {
            sb.append("[]");
            sb.append("\n");
            return;
        }

        sb.append("[");
        int size = arr.size();
        if(cursor != 0) {
            for (int i = 0; i < size-1; i++) {
                sb.append(arr.pollLast());
                sb.append(",");
            }
            sb.append(arr.pop());
        }
        else {
            for (int i = 0; i < size-1; i++) {
                sb.append(arr.pop());
                sb.append(",");
            }
            sb.append(arr.pop());
        }
        sb.append("]");
        sb.append("\n");
    }
}
