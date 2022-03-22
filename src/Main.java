import java.util.*;

class Solution {
    public long solution(int n, int[] times) {

<<<<<<< HEAD
    public static void main(String[] args) throws Exception {

=======
        Arrays.sort(times);
        long left = times[0];  // 1분 부터
        long right = (long)times[times.length-1]*(long)n;

        while(left < right) {
            long mid = (left + right) / 2;
            long number = getNumber(mid, times);
            if(number >= n) {
                right = mid;
            }
            else {
                left = mid+1;
            }
        }
        return right;
    }

    public static long getNumber(long time, int[] times) {
        long maxNumber = 0;
        for(int i = 0; i < times.length; i++) {
            maxNumber += (time /times[i]);
        }
        return maxNumber;
>>>>>>> b559430f6cbb03fe278f9cdbabf9ed63df9e8685
    }
}