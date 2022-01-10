import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] numbers;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //1부터 numbers[0] 까지의 자연수 중에서
        //numbers[1] 개로 구성된 수열을 출력

        int[] numberList  = new int[numbers[1]];

        back_tracking(0, numberList);

        System.out.println(sb);

    }

    public static void back_tracking(int numberCount, int[] numberList) {

        if(numberCount == numbers[1]) {
            //숫자의 개수가 numbers[1]개와 동일하다면
            //앞서 있던 모든 조건을 만족한다는 뜻이므로 답을 출력
            for (int i : numberList) {
                sb.append(i);
                sb.append(" ");
            }
            sb.append("\n");

        } else {
            //현재 상황 전까지는 조건대로 되어 있고,
            // 현재 상황인 numberCount 에 숫자를 추가하는 상황
            for(int i = 1; i<=numbers[0]; i++) {
                //1부터 numbers[0] 까지의 자연수 모두를 따져본다.
                int finalI = i;
                if(!Arrays.stream(numberList).filter(s -> s == finalI).findAny().isPresent()) {
                    //numberList 에 숫자가 없다면
                    numberList[numberCount] = i;
                    //지금의 numberList 상태를 저장하기 위해 clone 을 해줌
                    int[] numberList2 = numberList.clone();
                    //다음 차례로 진입
                    back_tracking(numberCount+1, numberList2);
                }
            }
        }
    }
}
