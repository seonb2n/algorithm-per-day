import java.util.*;


class Solution {

    /**
     * 연속된 AA, BB, CC 를 지우면 된다.
     * @param S
     * @return
     */

    Stack<Character> stack = new Stack<>();

    public String solution(String S) {
        if (S.equals("")) {
            return "";
        }

        stack.push(S.charAt(0));
        for (int i = 1; i < S.length(); i++) {
            char now = S.charAt(i);
            if (!stack.empty() && stack.peek() == now) {
                stack.pop();
            }
            else {
                stack.push(now);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

}


