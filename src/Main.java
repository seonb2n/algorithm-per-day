import java.util.*;

class Solution {
    public static PriorityQueue<CheckPoint> queue;
    public static long timeResult;

    public static void main(String[] args) {
        int[] time = {7, 10};
        solution(6, time);
    }

    public static long solution(int n, int[] times) {
        queue = new PriorityQueue<>();
        timeResult = 0;
        for(int i = 0; i < times.length; i++) {
            queue.offer(new CheckPoint(times[i]));
        }

        while(n > 0) {
            //사람 한 명을 가장 빨리 검색이 끝나는 검문대로 보낸다.
            CheckPoint minCheckPoint = queue.poll();
            minCheckPoint.remainedTime += minCheckPoint.time;
            queue.offer(minCheckPoint);
            n--;
        }

        while(!queue.isEmpty()) {
            timeResult = Math.max(timeResult, queue.poll().remainedTime);
        }

        return timeResult;
    }

    public static class CheckPoint implements Comparable<CheckPoint>{
        long remainedTime;
        int time;

        public CheckPoint(int time) {
            this.remainedTime = 0;
            this.time = time;
        }

        @Override
        public int compareTo(CheckPoint c) {
            long totalTime = this.remainedTime + this.time;
            long newTotalTime = c.remainedTime + c.time;
            return totalTime > newTotalTime ? 1 : -1;
        }
    }
}