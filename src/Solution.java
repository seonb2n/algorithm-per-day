import java.util.*;

class Solution {
    PriorityQueue<Job> pq = new PriorityQueue<>();
    Job[] jobArr;

    public int solution(int[][] jobs) {
        jobArr = new Job[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            jobArr[i] = new Job(jobs[i][0], jobs[i][1]);
        }

        Arrays.sort(jobArr, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.requestTime - o2.requestTime;
            }
        });

        int answer = 0;
        int nowTime = 0;
        int jobCount = 0;
        //가장 먼저 들어온 job 실행한 상태

        while (jobCount < jobArr.length) {
            //하나의 작업이 완료되는 시점까지 들어온 모든 요청을 큐에 추가
            int i = 0;
            while (i < jobArr.length && jobArr[i].requestTime <= nowTime) {
                if(!jobArr[i].isDone) {
                    pq.offer(jobArr[i]);
                    jobArr[i].isDone = true;
                }
                i++;
            }

            //큐가 비어있다면 작업이 없는 것
            if(pq.isEmpty()) {
                //다음 작업이 들어올 때까지 시간을 증가시킨다.
                nowTime++;
            }

            //작업 진행
            else {
                Job nowJob = pq.poll();
                nowTime += nowJob.taskTime;
                answer += (nowTime - nowJob.requestTime);
                jobCount++;
            }
        }

        answer = answer / jobArr.length;

        return answer;
    }

    public static class Job implements Comparable<Job>{
        int requestTime;
        int taskTime;
        boolean isDone;

        public Job(int requestTime, int taskTime) {
            this.requestTime = requestTime;
            this.taskTime = taskTime;
            isDone = false;
        }

        @Override
        public int compareTo(Job o) {
            return this.taskTime - o.taskTime;
        }
    }
}