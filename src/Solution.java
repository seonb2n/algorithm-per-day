import java.util.*;
import java.util.stream.Collectors;

class Solution {

    /**
     * A,B,C 중 하나라도 conform 하는 integer 의 총 개수
     *
     * @param A
     * @param B
     * @param C
     * @return
     */

    public static int solution(int A, int B, int C) {
        return findNumber(A) + findNumber(B) + findNumber(C) - findNumber(A | B) - findNumber(A | C) - findNumber(B | C) + findNumber(A | B | C);
    }

    static int findNumber(int target) {
        //target 을 30 자리로 만듦
        StringBuilder binaryTarget = new StringBuilder(Integer.toBinaryString(target));
        while (binaryTarget.length() < 30) {
            binaryTarget.insert(0, "0");
        }
        int result = 1;
        for (int i = 0; i < 30; i++) {
            if (binaryTarget.charAt(i) == '0') {
                result *= 2;
            }
        }
        return result;
    }

}

