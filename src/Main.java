import java.util.*;

class Solution {

    static HashMap<Long, Boolean> dp;
    public static int solution(int n, int k) {
        //n -> k진수
        String kNumber = "";
        if(k != 10) {
            while (n > 0) {
                kNumber =  (n % k) + kNumber;
                n = n / k;
            }
        } else {
            kNumber = String.valueOf(n);
        }
        int answer = 0;

        //k 진수 안에서 소수 찾기
        String[] numbers = kNumber.split("0");
        dp = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if(!numbers[i].equals("")) {
                long temp = Long.parseLong(numbers[i]);
                if(temp != 1) {
                    if(isRight(temp)) {
                        answer++;
                    }
                }
            }
        }

        return answer;
    }

    //n이 소수인지 검증하는 메서드
    public static boolean isRight(long n) {
        boolean result = true;
        if(dp.containsKey(n)) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                dp.put(n, false);
                return false;
            }
        }

        return result;
    }
}