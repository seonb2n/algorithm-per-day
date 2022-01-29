import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int caseNumber;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        caseNumber = Integer.parseInt(br.readLine());
        Dequeue dequeue = new Dequeue();

        for (int i = 0; i < caseNumber; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            switch (st.nextToken()) {
                case "push_back" : dequeue.push_back(Integer.parseInt(st.nextToken())); break;

                case "push_front" : dequeue.push_front(Integer.parseInt(st.nextToken())); break;

                case "front" : dequeue.front(); break;

                case "back" : dequeue.back(); break;

                case "size" : dequeue.size(); break;

                case "empty" : dequeue.empty(); break;

                case "pop_front" : dequeue.pop_front(); break;

                case "pop_back" : dequeue.pop_back(); break;
            }
        }

        sb = dequeue.sb;
        System.out.println(sb);
    }
}

class Dequeue {
    List<Integer> dequeue = new LinkedList<>();
    int size = 0;
    StringBuilder sb;

    public Dequeue() {
        sb = new StringBuilder();
    }

    public void push_front(int x) {
        dequeue.add(0, x);
        size++;
    }

    public void push_back(int x) {
        dequeue.add(x);
        size++;
    }

    public void pop_front() {
        if(size != 0) {
            sb.append(dequeue.get(0));
            sb.append("\n");
            dequeue.remove(0);
            size--;
        } else {
            sb.append("-1");
            sb.append("\n");
        }
    }

    public void pop_back() {
        if(size != 0) {
            sb.append(dequeue.get(size-1));
            sb.append("\n");
            dequeue.remove(size-1);
            size--;
        } else {
            sb.append("-1");
            sb.append("\n");
        }
    }

    public void size() {
        sb.append(size);
        sb.append("\n");
    }

    public void empty() {
        if(size != 0) {
            sb.append("0");
            sb.append("\n");
        } else {
            sb.append("1");
            sb.append("\n");
        }
    }

    public void front() {
        if(size != 0) {
            sb.append(dequeue.get(0));
            sb.append("\n");
        } else {
            sb.append("-1");
            sb.append("\n");
        }
    }

    public void back() {
        if(size != 0) {
            sb.append(dequeue.get(size-1));
            sb.append("\n");
        } else {
            sb.append("-1");
            sb.append("\n");
        }
    }
}