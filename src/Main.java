import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] numbers = new int[8001];
        List<Integer> sortedNumber = new ArrayList<>();

        double mean = 0;
        int median;
        int mode = 0;
        int range;

        //가장 많이 존재하는 값
        int max = 0;
        //겹칠 때, 두번째임을 확인
        boolean isSecond = false;

        for (int i = 0; i < testCaseNumber; i++) {
            int number = Integer.parseInt(br.readLine());
            numbers[number + 4000]++;
            mean += number;
        }

        //평균 구하기
        mean =Math.round(mean / testCaseNumber);
        sb.append((int) mean + "\n");

        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i] != 0) {

                if(numbers[i] > max) {
                    max = numbers[i];
                    mode = i-4000;
                    isSecond = false;
                } else if(numbers[i] == max) {
                    if(!isSecond) {
                        max = numbers[i];
                        mode = i-4000;
                        isSecond = true;
                    }
                }

                for (int j = 0; j < numbers[i]; j++) {
                    sortedNumber.add(i - 4000);
                }
            }
        }
        //중앙값 구하기
        median = sortedNumber.get(testCaseNumber / 2);
        sb.append(median + "\n");

        sb.append(mode + "\n");


        //범위 구하기
        range = sortedNumber.get(sortedNumber.size() - 1) - sortedNumber.get(0);
        sb.append(range);

        System.out.println(sb + "\n");
    }
}
