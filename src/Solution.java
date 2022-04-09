import java.util.*;

class Solution {
    static int N;
    static int[] newRock;

    public static int solution(int distance, int[] rocks, int n) {
        N = n;

        Arrays.sort(rocks);
        newRock = new int[rocks.length+1];
        for (int i = 0; i < rocks.length; i++) {
            newRock[i] = rocks[i];
        }
        newRock[rocks.length] = distance;

        int max = distance / (rocks.length - n + 1);
        int min = 0;

        while(min < max) {
            int mid = (min + max) / 2;
            int removedRock = removedRock(mid, newRock);

            if(removedRock > N) {
                max = mid;
            }
            else {
                min = mid+1;
            }
        }

        return min-1;
    }

    public static int removedRock(int distance, int[] rocks) {
        int removed = 0;
        int lastRock = 0;
        int nowRock = 0;
        for (int i = 0; i < rocks.length; i++) {
            nowRock = rocks[i];
            int nowDistance = nowRock - lastRock;
            if(nowDistance < distance) {
                removed++;
            }
            else {
                lastRock = nowRock;
            }
            if(removed > N) {
                break;
            }
        }

        return removed;
    }
}