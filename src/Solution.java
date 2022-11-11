import java.util.*;

class Solution {

    public static void main(String[] args) {

    }


    public static int solution(int[] A) {
        // write your code in Java SE 8
        HashSet<Integer> set = new HashSet<Integer>();

        int result = 1;
        for (int j : A) {
            set.add(j);
        }

        while (true) {
            if(set.contains(result)) {
                result++;
            }
            else {
                break;
            }
        }
        return result;
    }
}