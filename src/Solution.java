import java.util.Arrays;

class Solution {

    private static final int[] noAnswer = {-1};

    static int sum = 0;

    public int[] solution(int n, int s) {
        int[] answer = new int[n];

        answer[0] = s / n;
        sum += answer[0];
        if (answer[0] == 0) {
            return noAnswer;
        }

        for (int i = 1; i < n; i++) {
            answer[i] = s / n;
            sum += answer[i];
        }

        sum = s - sum;

        // 합이 s 가 될 때까지 뒤에다 더함
        for (int i = 0; i < sum; i++) {
            answer[n-1-i] ++;
        }


        return answer;
    }
}