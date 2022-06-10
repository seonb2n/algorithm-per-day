import java.util.Arrays;
import java.util.HashSet;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {

        long start = 1L;
        long end = 4 * 100000000000000L;

        //이분탐색으로 만족하는 시간 값을 찾아야 한다.
        while(start < end) {
            long mid = (start + end) / 2;
            if(check(mid, a, b, g, s, w, t)) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return end;
    }

    //이분탐색으로 주어진 시간 안에 a, b 를 만족시키는 금과 은을 운반할 수 있는지 확인하는 메서드
    private boolean check(long mid, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        //시간 안에 옮길 수 있는 최대 자원 량
        long total = 0;
        //시간 안에 금 우선으로 옮길 때 옮길 수 있는 최대 금량
        long maxGold = 0;
        //시간 안에 은 우선으로 옮길 때 옮길 수 있는 최대 은량
        long maxSilver = 0;
        for (int i = 0; i < g.length; i++) {
            //총 트럭이 움직일 수 있는 횟수
            long nowMoveCount = mid / (t[i] * 2L);
            //편도로 한 번 더 갈 여유가 있으면 +1
            if(mid % (t[i] * 2L) >= t[i]) {
                nowMoveCount++;
            }
            //트럭이 해당 도시에서 운반할 수 있는 최대량
            long maxCarry = Math.min(nowMoveCount * w[i], g[i] + s[i]);
            total += maxCarry;

            maxGold += Math.min(maxCarry, g[i]);
            maxSilver += Math.min(maxCarry, s[i]);
        }
        // a <= 최대 골드, b <= 최대 은 이면서 a+b <= 시간안에 옮기는 무게라면 return true
        return a <= maxGold && b <= maxSilver && ((long)a + (long)b) <= total;
    }
}