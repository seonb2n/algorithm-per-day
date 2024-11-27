import java.util.*;

// https://leetcode.com/problems/multiply-strings/
class Solution {
    public String multiply(String num1, String num2) {
        if (num2.equals("0") || num1.equals("0")) {
            return "0";
        }

        List<Integer> res = new LinkedList<>();
        for (int i = num2.length() - 1; i >= 0; i--) {
            int cursor = num2.length() - i - 1;  // queue의 시작 위치
            int now = num2.charAt(i) - '0';
            int prev = 0;

            // 초기 0들 처리
            for (int j = 0; j < cursor; j++) {
                if (res.size() - 1 < j) {
                    res.add(0);
                }
            }

            // num1과 곱하면서 바로 결과에 더하기
            for (int j = num1.length() - 1; j >= 0; j--) {
                int nowInt = (num1.charAt(j) - '0') * now + prev;
                prev = nowInt / 10;
                nowInt %= 10;

                if (res.size() - 1 < cursor) {
                    res.add(nowInt);
                } else {
                    nowInt += res.get(cursor);
                    prev += nowInt / 10;
                    nowInt %= 10;
                    res.set(cursor, nowInt);
                }
                cursor++;
            }

            if (prev != 0) {
                res.add(prev);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = res.size() - 1; i >= 0; i--) {
            sb.append(res.get(i));
        }
        return sb.toString();
    }
}
