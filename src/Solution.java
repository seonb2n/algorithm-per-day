import java.util.*;

class Solution {

    public static int solution(int[] stones, int k) {
        int answer = 0;

        // 이진 탐색으로 풀어야 함
        int min = 1;
        int max = 200_000_000;
        while (min <= max) {
            int mid = (min + max) / 2;
            // mid 로 강을 건널 수 있으면 min 을 올린다.
            if (canCrossRiver(stones, k, mid)) {
                min = mid + 1;
                // 정답은 강을 건널 수 있는 최대 값
                answer = Math.max(answer, mid);
            }
            // 강을 건널 수 없으면 max 를 줄인다.
            else {
                max = mid-1;
            }
        }
        
        return answer;
    }
    
    // set 안에 연속된 숫자가 k 개 만큼 존재하는지 확인한다.
    public static boolean canCrossRiver(int[] stones, int k, int friendsNum) {
        int skip = 0;

        for (int stone : stones) {
            // 못 건너는 돌이다.
            if (stone < friendsNum) {
                skip++;
            }
            else {
                skip= 0;
            }

            if (skip == k) {
                return false;
            }
        }

        return true;
    }

}