import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static StringBuilder sb;
    static int[] numbers;
    static int[] dpForUp;
    static int[] dpForDown;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int caseNumber = Integer.parseInt(br.readLine());

        numbers = new int[caseNumber+1];
        dpForUp = new int[caseNumber+1];
        dpForDown = new int[caseNumber+1];

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < caseNumber; i++) {
            getNumber(i);
            max = Math.max(max, Math.max(dpForUp[i], dpForDown[i]));
        }

        System.out.println(max);
    }

    private static void getNumber(int i) {
        if(i == 0) {
            dpForUp[0] = 1;
            dpForDown[0] = 1;
            return;
        } else if(i == 1) {
            if(numbers[1] > numbers[0]) {
                dpForUp[1] = 2;
                dpForDown[1] = 1;
            } else if (numbers[1] < numbers[0]){
                dpForUp[1] = 1;
                dpForDown[1] = 2;
            } else {
                dpForUp[1] = 1;
                dpForDown[1] = 1;
            }
            return;
        }

        int max1 = 0;
        boolean valueChanged = false;
        //점점 증가하는 형태
        for (int j = 0; j < i; j++) {
            //j 의 숫자가 i보다 작다면
            if (numbers[j] < numbers[i]) {
                //그 중에 results[j] 값이 가장 큰 놈을 찾는다.
                if (max1 < dpForUp[j]) {
                    max1 = dpForUp[j];
                    valueChanged = true;
                }
            }
        }
        if(valueChanged) {
            dpForUp[i] = max1 + 1;
        } else {
            dpForUp[i] = 1;
        }

        valueChanged = false;
        int max2 = 0;
        //내려오는 형태
        for (int j = 0; j < i; j++) {
            //j 의 숫자가 i 보다 큰 값 중에 dp 값이 제일 큰 값을 찾는다.
            if (numbers[j] > numbers[i]){
                if (max2 < Math.max(dpForDown[j], dpForUp[j])) {
                    max2 = Math.max(dpForDown[j], dpForUp[j]);
                    valueChanged = true;
                }
            }
        }

        if(valueChanged) {
            dpForDown[i] = max2 + 1;
        } else {
            dpForDown[i] = 1;
        }
    }
}
