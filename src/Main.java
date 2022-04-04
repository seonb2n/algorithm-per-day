import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static int N;
    static int K;
    static long motherNumber;
    static long resultNumber;
    static char[][] numbers;
    static int[] remainedNumber;
    static BufferedReader br;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new char[N][];
        remainedNumber = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = br.readLine().toCharArray();
        }
        K = Integer.parseInt(br.readLine());

        //numbers 의 각 숫자들에 대해서 나머지화해야 한다.
        for (int i = 0; i < N; i++) {
            makeRemained(i, numbers[i]);
        }

        //분모는 N ! 이다.
        motherNumber = 1;
        for (int i = 2; i <= N; i++) {
            motherNumber = motherNumber * i;
        }

        //dp 를 사용해서 특정 숫자들을 사용했을 때, 해당 숫자가 나눠지는지를 마킹해야 한다.
        //dp[i][j] 는 비트마스킹 된 i 를 이어붙인 숫자를 k 로 나눴을 때 나머지가 j 인 경우의 수이다.
        dp = new long[1 << N][K];
        for (int i = 0; i < (1 << N); i++) {
            Arrays.fill(dp[i], -1);
        }

        resultNumber = DFS(0, 0);

        if(resultNumber == 0) {
            System.out.print("0/1");
        }
        else if(motherNumber == resultNumber) {
            System.out.print("1/1");
        }
        else {
            long gcd = findGCD(motherNumber, resultNumber);
            motherNumber = motherNumber / gcd;
            resultNumber = resultNumber / gcd;

            System.out.print(resultNumber+"/"+motherNumber);
        }
    }

    //최대 공약수를 찾는 메서드
    private static long findGCD(long motherNumber, long resultNumber) {
        while(motherNumber % resultNumber != 0 ) {
            long temp = motherNumber % resultNumber;
            motherNumber = resultNumber;
            resultNumber = temp;
        }
        return resultNumber;
    }

    //비트마스킹으로 처리된 방문 상태, 지금 가지고 있는 나머지를 인자로 한다.
    //지금까지 방문한 곳이 nowStatus 이고 현재 나머지가 rest 일 때, 앞으로 빙문 안한 숫자들을 추가하면 가질 수 있는 나머지의 경우의 수를 반환한다.
    public static long DFS(int nowStatus , int rest) {
        //끝까지 모두 방문
        if(nowStatus == (1 << N) -1) {
            if (rest == 0) {
                return 1L;
            } else {
                return 0;
            }
        }

        //이미 탐색한 지점은 그대로 반환한다.
        if(dp[nowStatus][rest] != -1) {
            return dp[nowStatus][rest];
        }

        //지금 까지 방문하지 않은 곳들 중 하나를 방문한다.
        for (int i = 0; i < N; i++) {
            //방문한 곳과 방문하고자 하는 곳을 대조해서 확인한다
            int nextNumber = 1<<i;
            //다음 숫자를 아직 탐색하지 않았다면 탐색을 진행한다.
            if((nowStatus & nextNumber) == 0) {
                dp[nowStatus][rest] +=
                        DFS(nowStatus | nextNumber, getRemainedNumber(rest, remainedNumber[i]));
            }
        }
        dp[nowStatus][rest]++;
        return dp[nowStatus][rest];
    }

    //number1 에다가 number2 를 붙인 경우의 나머지를 반환한다.
    public static int getRemainedNumber(long number1, long number2) {
        String str = number1 + String.valueOf(number2);
        return Integer.parseInt(str) % K;
    }

    public static void makeRemained(int numberId, char[] number) {
        int restNumber = Integer.parseInt(String.valueOf(number[0])) % K;
        for (int i = 1; i < number.length; i++) {
            int nowNumber = restNumber * 10 + Integer.parseInt(String.valueOf(number[i]));
            restNumber = nowNumber % K;
        }
        remainedNumber[numberId] = restNumber;
    }
}