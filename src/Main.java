import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb;
    static int[] memoOfZero;
    static int[] memoOfOne;
    static boolean[] calculatedValueForZero;
    static boolean[] calculatedValueForOne;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        memoOfZero = new int[41];
        memoOfOne = new int[41];
        calculatedValueForZero = new boolean[41];
        calculatedValueForOne = new boolean[41];

        memoOfZero[0] = 1;
        memoOfZero[1] = 0;
        memoOfOne[0] = 0;
        memoOfOne[1] = 1;
        calculatedValueForZero[0] = true;
        calculatedValueForZero[1] = true;
        calculatedValueForOne[0] = true;
        calculatedValueForOne[1] = true;

        int testCaseNumber = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseNumber; i++) {
            int number = Integer.parseInt(br.readLine());
            sb.append(fibonacciForZero(number));
            sb.append(" ");
            sb.append(fibonacciForOne(number));
            sb.append("\n");
        }

        System.out.println(sb);

    }

    //0이 총 몇번 호출됐는지를 구하는 함수
    private static int fibonacciForZero(int nowNumber) {

        //nowNumber 가 이미 존재하는 값이라면,
        //값을 반환
        if(calculatedValueForZero[nowNumber]) {
            return memoOfZero[nowNumber];
        } else {
            //nowNumber 가 아직 계산되지 않은 값이라면,
            //쪼개서 계산하고, 계산된 값을 반환
            //계산이 됐기 때문에 calculated 를 true 로 변환
            memoOfZero[nowNumber] = fibonacciForZero(nowNumber-1) + fibonacciForZero(nowNumber-2);
            calculatedValueForZero[nowNumber] = true;
            return memoOfZero[nowNumber];
        }
    }


    private static int fibonacciForOne(int nowNumber) {
        if(calculatedValueForOne[nowNumber]) {
            return memoOfOne[nowNumber];
        } else {
            //nowNumber 가 아직 계산되지 않은 값이라면,
            //쪼개서 계산하고, 계산된 값을 반환
            //계산이 됐기 때문에 calculated 를 true 로 변환
            memoOfOne[nowNumber] = fibonacciForOne(nowNumber-1) + fibonacciForOne(nowNumber-2);
            calculatedValueForOne[nowNumber] = true;
            return memoOfOne[nowNumber];
        }
    }
}
