import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int caseNumber;
    static StringBuilder sb;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        caseNumber = Integer.parseInt(br.readLine());
        queue = new LinkedList<>();

        getNumber();
    }

    private static void getNumber() {
        int cardNumber = caseNumber;
        //카드를 순서대로 쌓아둔다.
        for (int i = 0; i < cardNumber; i++) {
            queue.add(i+1);
        }

        //card 의 개수가 1이 될 때까지 행동을 반복한다.
        while (cardNumber != 1) {
            //카드의 개수가 2개 이상이라면
            if(cardNumber >= 2) {
                //맨 위의 카드를 버린다.
                queue.poll();
                cardNumber--;
            }
            //맨 위의 카드를 가장 밑으로 넣는다.
            queue.add(queue.poll());
        }
        System.out.println(queue.poll());
    }
}