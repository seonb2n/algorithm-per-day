import java.util.*;

class Solution {

    static int N = 0;
    static int max = 0;
    static int[] STICKERS;
    public int solution(int sticker[]) {
        int answer = 0;
        N = sticker.length;
        STICKERS = new int[N];

        if (sticker.length == 1) {
            return sticker[0];
        }

        for (int i = 0; i < N; i++) {
            STICKERS[i] = sticker[i];
        }
        //dfs
        Set<Integer> detached = new HashSet<Integer>();
        detached.add(0);
        dfs(detached, STICKERS[0], 0);
        detached.add(1);
        if (N > 2) {
            detached.add(2);
        }
        dfs(detached, STICKERS[1], 1);

        return max;
    }

    void dfs(Set<Integer> detached, int nowSum, int lastNode) {
        if (detached.size() == N) {
            max = Math.max(max, nowSum);
        }
        else {
            //last node 에서 두칸을 가는 경우
            int nextNode = lastNode + 2;
            if (!detached.contains(nextNode) && nextNode < N) {
                Set<Integer> newSet = new HashSet<>(Set.copyOf(detached));
                newSet.add(nextNode);
                if (nextNode + 1 < N) {
                    newSet.add(nextNode+1);
                }
                dfs(newSet, nowSum+STICKERS[nextNode], nextNode);
            }
            //last node 에서 세칸을 가는 경우
            nextNode = lastNode + 3;
            if (!detached.contains(nextNode) && nextNode < N) {
                Set<Integer> newSet = new HashSet<>(Set.copyOf(detached));
                newSet.add(nextNode);
                newSet.add(nextNode-1);
                if (nextNode + 1 < N) {
                    newSet.add(nextNode+1);
                }
                dfs(newSet, nowSum+STICKERS[nextNode], nextNode);
            }
        }
    }

}
