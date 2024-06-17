import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// https://leetcode.com/problems/count-pairs-that-form-a-complete-day-ii/
class Solution {
    // 거꾸로, 내 값 이전에 나랑 합칠 수 있는 나머지를 가진 개수를 찾으면 된다.
    public long countCompleteDayPairs(int[] hours) {
        int n = hours.length;
        long cnt = 0;
        int[] counts = new int[24];

        for (int hour : hours) {
            int num = hour % 24;
            int complement = (24 - num) % 24;

            cnt += counts[complement];

            counts[num]++;
        }

        return cnt;
    }
}
