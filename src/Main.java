import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int N;
    static PriorityQueue<Integer> minQueue;
    static int minSize = 0;
    static PriorityQueue<Integer> maxQueue;
    static int maxSize = 0;
    static int middleNumber;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        //오름 차순 정렬된 큐
        minQueue = new PriorityQueue<>();
        //내림차순 정렬된 큐
        maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        middleNumber = Integer.parseInt(br.readLine());
        sb.append(middleNumber);
        sb.append("\n");

        for (int i = 1; i < N; i++) {
            addNumber(Integer.parseInt(br.readLine()));
            sb.append(middleNumber);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    //middle number 를 기준으로 값을 적절한 큐에 할당하는 함수
    public static void addNumber(int number) {
        if(number >= middleNumber) {
            if(maxSize == minSize) {
                minQueue.add(number);
                minSize++;
            }
            else {
                maxQueue.add(middleNumber);
                maxSize++;
                minQueue.add(number);
                middleNumber = minQueue.poll();
            }
        }

        else {
            if(maxSize == minSize) {
                minQueue.add(middleNumber);
                minSize++;
                maxQueue.add(number);
                middleNumber = maxQueue.poll();
            }
            else {
                maxQueue.add(number);
                maxQueue.add(middleNumber);
                middleNumber = maxQueue.poll();
                maxSize++;
            }
        }
    }

}
