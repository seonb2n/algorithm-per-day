import java.util.*;

class Solution {

    /**
     * A,B 개수만큼 이루어진 배열을 조합해라. 단 3개 이상 같은 문자가 연속할 수 없다.
     * @param A
     * @param B
     * @return
     */
    static StringBuilder sb = new StringBuilder();

    public String solution(int A, int B) {
        if (A == 0) {
            while (B > 0) {
                sb.append('b');
                B--;
            }
            return sb.toString();
        }

        if (B == 0) {
            while (A > 0) {
                sb.append('a');
                A--;
            }
            return sb.toString();
        }

        // A 와 B 의 개수를 같게 만들어준다.
        if (A > B) {
            while (A != B && A > 0 && B > 0) {
                sb.append('a');
                A--;
                sb.append('a');
                A--;
                sb.append('b');
                B--;
            }
            // A B 하나씩 번갈아가면서 더하거나, 남은 한쪽만 더함.
            while (A != 0 || B != 0) {
                if (A != 0) {
                    sb.append('a');
                    A--;
                }
                if (B != 0) {
                    sb.append('b');
                    B--;
                }
            }
        }
        else if (A < B) {
            while (A != B && A > 0 && B > 0) {
                sb.append('b');
                B--;
                sb.append('b');
                B--;
                sb.append('b');
                A--;
            }
            // A B 하나씩 번갈아가면서 더하거나, 남은 한쪽만 더함.
            while (A != 0 || B != 0) {
                if (B != 0) {
                    sb.append('b');
                    B--;
                }
                if (A != 0) {
                    sb.append('a');
                    A--;
                }
            }
        }
        else {
            while (A != 0 || B != 0) {
                if (B != 0) {
                    sb.append('b');
                    B--;
                }
                if (A != 0) {
                    sb.append('a');
                    A--;
                }
            }
        }
        return sb.toString();
    }
}

