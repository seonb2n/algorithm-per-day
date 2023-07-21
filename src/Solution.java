import java.util.*;

class Solution {

    static int N;

    public int solution(int sticker[]) {
        N = sticker.length;

        // memo[i] 는 i 칸 까지의 최대 수
        // memo[i] = Math.max(memo[i-2] + sticker[i], memo[i-3] + sticker[i], memo[i-1]);
        int[] memo = new int[N];
        memo[0] = sticker[0];
        if (N == 1) {
            return memo[0];
        }
        memo[1] = Math.max(memo[0], sticker[1]);
        if (N == 2) {
            return memo[1];
        }
        memo[2] = Math.max(Math.max(sticker[0], sticker[1]), sticker[2]);
        if (N == 3) {
            return memo[2];
        }

        int max = 0;

        //0 을 무조건 넣는 경우
        memo[0] = sticker[0];
        memo[1] = sticker[0];
        memo[2] = memo[0] + sticker[2];
        int index = 3;
        while(index < N-1) {
            memo[index] = Math.max(Math.max(memo[index-1], memo[index-2] + sticker[index]), memo[index-3] + sticker[index]);
            index++;
        }

        memo[N-1] = Math.max(memo[N-2], memo[N-3]);
        max = Math.max(max, memo[N-1]);

        memo = new int[N];
        //0 을 무조건 넣지 않는 경우
        memo[0] = 0;
        memo[1] = sticker[1];
        memo[2] = Math.max(memo[1], sticker[2]);
        index = 3;
        while(index < N-1) {
            memo[index] = Math.max(Math.max(memo[index-1], memo[index-2] + sticker[index]), memo[index-3] + sticker[index]);
            index++;
        }

        memo[N-1] = Math.max(memo[N-2], memo[N-3] + sticker[N-1]);
        max = Math.max(max, memo[N-1]);

        return max;
    }


}
