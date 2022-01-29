import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static long caseNumber;
    static StringBuilder sb;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        caseNumber = Long.parseLong(br.readLine());
        sb = new StringBuilder();
        Queue queue = new Queue();

        for (int i = 0; i < caseNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            switch (st.nextToken()) {
                case "push" : queue.push(Integer.parseInt(st.nextToken())); break;

                case "front" : queue.front(); break;

                case "back" : queue.back(); break;

                case "size" : queue.size(); break;

                case "empty" : queue.empty(); break;

                case "pop" : queue.pop(); break;
            }
        }

        System.out.println(sb);
    }
}

class Queue {
    List<Integer> list;
    int count;

    public Queue() {
        list = new LinkedList<>();
        count = 0;
    }


    public void push(int x) {
        list.add(x);
        count++;
    }

    public void pop() {
        if(count != 0) {
            Main.sb.append(list.get(0));
            Main.sb.append("\n");
            list.remove(0);
            count--;
        } else {
            Main.sb.append("-1");
            Main.sb.append("\n");
        }
    }

    public void size() {
        Main.sb.append(count);
        Main.sb.append("\n");
    }

    public void empty() {
        if(count == 0) {
            Main.sb.append(1);
            Main.sb.append("\n");
        } else {
            Main.sb.append(0);
            Main.sb.append("\n");
        }
    }

    public void front() {
        if(count != 0) {
            Main.sb.append(list.get(0));
            Main.sb.append("\n");
        } else {
            Main.sb.append("-1");
            Main.sb.append("\n");
        }
    }

    public void back() {
        if(count != 0) {
            Main.sb.append(list.get(count-1));
            Main.sb.append("\n");
        } else {
            Main.sb.append("-1");
            Main.sb.append("\n");
        }
    }
}

