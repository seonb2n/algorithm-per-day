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

    static List<Integer> result;
    //numbers 의 각각의 숫자가 몇 번째 index 에 들어갔는지를 기록하는 배열
    static int[] numberIndex;
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new ArrayList<>();
        numberIndex = new int[numbers.length];
        result.add(Integer.MIN_VALUE);
        stack = new Stack<>();

        //이분탐색을 이용해서 result 를 채워준다.
        for (int i = 0; i < numbers.length; i++) {
            int nowNumber = numbers[i];

            //nowNumber 은 result 에 있는 숫자 중에 자기보다 작은 것 다음 위치에 들어간다.
            int left = 0;
            int right = result.size()-1;
            if(nowNumber > result.get(result.size() - 1)) {
                result.add(nowNumber);
                numberIndex[i] = result.size() - 1;
            }
            else {
                while(left < right) {
                    int mid = (left + right) / 2;
                    if(result.get(mid) >= nowNumber) {
                        right = mid;
                    }
                    else {
                        left = mid + 1;
                    }
                }
                result.set(right, nowNumber);
                numberIndex[i] = right;
            }
        }

        sb.append(result.size()-1);
        sb.append("\n");

        //resultIndex 에 들어있는 것을 바탕으로 역순으로 가져온다.
        int startSize = result.size() - 1;
        for (int i = numberIndex.length - 1; i >= 0; i--) {
            if(numberIndex[i] == startSize) {
                stack.push(numbers[i]);
                startSize--;
            }
            if(startSize == 0) {
                break;
            }
        }

        while (!stack.empty()) {
            sb.append(stack.pop());
            sb.append(" ");
        }

        System.out.println(sb);
    }
}