import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static int caseNumber;
    static StringTokenizer st;
    static StringBuilder sb;
    static Stack<Integer> stack;
    static int[] numbers;
    static int[] results;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        stack = new Stack<>();

        caseNumber = Integer.parseInt(br.readLine());
        numbers = new int[caseNumber+1];
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        results = new int[caseNumber];

        getNGE();

        for (int i = 0; i < caseNumber; i++) {
            sb.append(results[i]);
            sb.append(" ");
        }
        System.out.println(sb);
    }

    private static void getNGE() {
        //stack 을 순회한다.
        stack.push(0);
        for (int i = 1; i < caseNumber; i++) {
            if(numbers[i] <= numbers[i-1]) {
                stack.push(i);
            }
            //i 번째 숫자가 i-1 숫자보다 커지면,
            else {
                //stack 에 들어있는 값들을 확인하며, 해당 값이 i 번째 보다 작다면 results 에 numbers[i] 를 대입한다.
                while(!stack.empty()) {
                    int temp = stack.pop();
                    if(numbers[temp] < numbers[i]) {
                        results[temp] = numbers[i];
                    } else {
                        stack.push(temp);
                        break;
                    }
                }
                stack.push(i);
            }
        }
        //마지막까지 남아있는 값들은 -1 을 results 값으로 가진다
        while (!stack.empty()) {
            results[stack.pop()] = -1;
        }
    }
}

