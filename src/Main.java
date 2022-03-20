import java.util.*;

class Solution {
    static numbers[] dp;
    static int N;
    static int targetNumber;

    public int solution(int n, int number) {
        int N = n;
        targetNumber = number;
        dp = new numbers[9];
        dp[1] = new numbers(1);
        dp[1].numberList.add(N);

        for(int i = 2; i<=8; i++) {
            //N을 i 개 사용해서 만들 수 있는 수들을 추가해준다.
            //N 을 i 개 사용해 만들 수 있는 수는, i-1 과 1의 사칙 연산, .. 1과 i-1 의 사칙연산
            dp[i] = new numbers(i);
            for(int j = 1; j < i; j++) {
                Set<Integer> firstSet = dp[j].numberList;
                Set<Integer> secondSet = dp[i-j].numberList;

                for(int firstNumber : firstSet) {
                    for(int secondNumber : secondSet) {
                        dp[i].numberList.add(firstNumber + secondNumber);
                        dp[i].numberList.add(firstNumber * secondNumber);
                        dp[i].numberList.add(firstNumber - secondNumber);
                        if(firstNumber != 0 && secondNumber != 0) {
                            dp[i].numberList.add(firstNumber / secondNumber);
                        }
                    }
                }
            }
            dp[i].numberList.add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }
        for(int i = 1; i <= 8; i++) {
            if(dp[i].numberList.contains(number)) {
                return i;
            }
        }

        int answer = -1;
        return answer;
    }

    public static class numbers {
        int usedNumber;
        Set<Integer> numberList;

        public numbers(int usedNumber) {
            this.usedNumber = usedNumber;
            numberList = new HashSet<>();
        }
    }
}