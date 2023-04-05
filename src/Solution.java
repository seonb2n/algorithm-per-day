import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    public static void main(String[] args) {
        int[] works = {4, 3, 3};
        solution(4, works);
    }

    public static long solution(int n, int[] works) {
        long answer = 0;
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
        }

        // 야근 지수가 가장 큰 놈을 빼줘야 함
        for (int i = 0; i < n; i++) {
            Integer a = pq.poll();
            if (a == 0) {
                break;
            }
            a--;
            pq.offer(a);
        }

        while (!pq.isEmpty()) {
            int num = pq.poll();
            answer += (long) num * num;
        }


        return answer;
    }

}