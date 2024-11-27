import java.util.*;

// https://leetcode.com/problems/multiply-strings/
class Solution {
    public String multiply(String num1, String num2) {
        if (num2.equals("0") || num1.equals("0")) {
            return "0";
        }

        List<Integer> res = new LinkedList<>();
        for (int i = num2.length() - 1; i >= 0; i--) {
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < num2.length() - i - 1; j++) {
                queue.add(0);
            }
            int now = num2.charAt(i) - '0';
            // num1 을 1개씩 돌면서 now 랑 곱하기
            int prev = 0;
            for (int j = num1.length() - 1; j >= 0; j--) {
                char target =  num1.charAt(j);
                int nowInt = (target - '0') * (now);
                nowInt += prev;
                prev = nowInt / 10;
                queue.add(nowInt % 10);
            }
            if (prev != 0) {
                queue.add(prev);
            }
            // queue 를 하나씩 꺼내서 res 에 더한다.
            int prevNum = 0;
            int cursor = 0;
            while (!queue.isEmpty()) {
                int nowInt = queue.poll();
                nowInt += prevNum;
                if (res.size() - 1 < cursor) {
                    prevNum = nowInt / 10;
                    nowInt = nowInt % 10;
                    res.add(nowInt);
                } else {
                    nowInt += res.get(cursor);
                    prevNum = nowInt / 10;
                    nowInt %= 10;
                    res.set(cursor, nowInt);
                }
                cursor++;
            }
            if (prevNum != 0) {
                res.add(prevNum);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = res.size() - 1; i >= 0; i--) {
            sb.append(res.get(i));
        }
        return sb.toString();
    }
}
