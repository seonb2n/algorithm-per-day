<<<<<<< HEAD
class Main {



}
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Main {

    static BufferedReader br;
    static int N;
    static int[] numbers;
    static int[] numbersIndex;
    static List<Integer> stack;
    static int LCS;
    static int[] answers;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        numbersIndex = new int[N];
        Arrays.fill(numbersIndex, -999);
        stack = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            findLCS(i);
        }

        LCS = stack.size();
        answers = new int[LCS];

        int size = answers.length;
        for (int i = N-1; i >= 0; i--) {
            if(numbersIndex[i] == size-1) {
                answers[size-1] = numbers[i];
                size--;
            }
        }

        System.out.println(LCS);
        for (int i = 0; i < LCS; i++) {
            sb.append(answers[i]);
            sb.append(" ");
        }
        System.out.print(sb);
    }

    public static void findLCS(int numberIndex) {
        int nowNumber = numbers[numberIndex];
        int stackSize = stack.size();
        if(stackSize == 0) {
            stack.add(nowNumber);
            numbersIndex[numberIndex] = 0;
        }
        else if(stack.get(stackSize - 1) < nowNumber) {
            stack.add(nowNumber);
            numbersIndex[numberIndex] = stackSize;
        }
        else {
            //스택의 수를 하나씩 역순으로 본다.
            boolean isAdded = false;
            for (int i = stackSize-1; i >= 0; i--) {
                int temp = stack.get(i);
                //집어 넣고자 하는 수가 temp 보다는 작되, 최대한 앞쪽에 위치해야 한다.
                if(temp >= nowNumber) {
                    continue;
                }
                stack.remove(i+1);
                stack.add(i+1, nowNumber);
                numbersIndex[numberIndex] = i+1;
                isAdded = true;
                break;
            }
            if(stack.get(0) > nowNumber && !isAdded) {
                stack.remove(0);
                stack.add(0, nowNumber);
                numbersIndex[numberIndex] = 0;
            }
        }
    }
}
>>>>>>> c0f1286166f4fb1ada435e76e49c0693eb4aee5a
