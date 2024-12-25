// https://leetcode.com/problems/permutations=ii

import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int nowLevel = 1;
        // diffs 의 max 부터 탐색하면서
        int maxDiff = 0;
        for (int i = 0; i < diffs.length; i++) {
            maxDiff = Math.max(diffs[i], maxDiff);
        }

        int left = 1;
        int right = maxDiff;
        while(left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(mid, diffs, times, limit)) {
                answer = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return answer;
    }


    private boolean isPossible(int level, int[] diffs, int[] times, long limit) {
        long totalTime = 0;

        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                totalTime += times[i];
            } else {
                long extraTime;
                if (i > 0) {
                    extraTime = (long)(times[i-1] + times[i]) * (diffs[i] - level) + times[i];
                } else {
                    extraTime = (long)times[i] * (diffs[i] - level) + times[i];
                }

                // 오버플로우 체크
                if (extraTime < 0 || totalTime + extraTime > limit) {
                    return false;
                }
                totalTime += extraTime;
            }

            if (totalTime > limit) {
                return false;
            }
        }

        return true;
    }
}
