import java.util.*;

class Solution {
    /**
     * N 을 이진수로 변환했을 때, 1 사이에 있는 가장 긴 0의 연속을 구해야 함.
     * @param N
     * @return
     */
    public int solution(int N) {
        int result  = 0;
        String binaryString = Integer.toBinaryString(N);

        // 전체 탐색
        int nowLen = 0;
        for (int i = 0; i < binaryString.length()-1; i++) {
            if (binaryString.charAt(i) == '1') {
                result = Math.max(result, nowLen);
                nowLen = 0;
            }
            else {
                nowLen++;
            }
        }

        // 마지막 숫자가 1이면 비교를 한다.
        if (binaryString.charAt(binaryString.length()-1) == '1') {
            result = Math.max(result, nowLen);
        }

        return result;
    }
}
