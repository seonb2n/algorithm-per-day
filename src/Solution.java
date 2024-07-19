import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/214288?language=java
class Solution {
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;

        // 각 유형별 그룹화
        List[] group = new List[k+1];

        for (int i = 0; i <= k; i++) {
            group[i] = new ArrayList<Meet>();
        }
        for (int[] nowReq : reqs) {
            group[nowReq[2]].add(new Meet(nowReq[0], nowReq[0] + nowReq[1]));
        }


        // 각 유형별 상담사 1 명 ~ n-k+1 명까지 추가될 수록 대기 시간 계산
        // i 유형에 상담사가 j 명 있을 때 걸리는 총 대기 시간
        int[][] timePerNumber = new int[k+1][n+1];

        for (int i = 0; i < k+1; i++) {
            // 해당 타입을 신청한 지원자가 없다면 continue
            if (group[i].isEmpty()) continue;

            for (int j = 1; j <= (n-k+1) ; j++) {

                int waitTime = calculationTime(group[i], j);
                timePerNumber[i][j] = waitTime;
            }
        }

        // 각 상담원을 1명씩 배치
        int[] currentCounselor = new int[k+1];

        for (int i = 1; i < k + 1; i++) {
            currentCounselor[i] = 1;
        }

        int remainCounselors = n - k;

        // 대기시간이 가장 많이 줄어드는 곳에 1명씩 배치
        while (remainCounselors > 0) {

            int maxReduceTime = 0;

            int nowType = 0;

            for (int i = 1; i < k+1 ; i++) {
                int nowTypeNumber = currentCounselor[i];

                int waitingTime = timePerNumber[i][nowTypeNumber];

                int next = timePerNumber[i][nowTypeNumber + 1];
                int reduceTime = waitingTime - next;

                if (reduceTime >= maxReduceTime) {
                    maxReduceTime = reduceTime;
                    nowType = i;
                }
            }

            if (maxReduceTime == 0) break;;

            currentCounselor[nowType]++;
            remainCounselors--;
        }

        // 배치가 끝나고 계산
        for (int i = 1; i < k + 1; i++) {
            if (group[i].isEmpty()) continue;

            int counselors = currentCounselor[i];

            answer += timePerNumber[i][counselors];
        }

        return answer;
    }

    private int calculationTime(List<Meet> typeTime, int counselorNumber) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 끝나는 시간 저장
        int waitTime = 0;

        for (Meet t : typeTime) { // 해당 타입의 상담 정보들을 가져옴
            if (pq.isEmpty() || pq.size() < counselorNumber) { // 상담원수가 남아 있을 때
                pq.add(t.end);
            } else {
                // 현재 진행중인 상담중 가장 빨리 끝나는 시간
                int earlyConsultEndTime = pq.poll();

                if (t.start < earlyConsultEndTime) { // 대기시간이 있는 경우
                    waitTime += (earlyConsultEndTime - t.start);
                    pq.add(earlyConsultEndTime + (t.end - t.start));
                } else {
                    pq.add(t.end); // 대기시간이 없는 경우 종료시간 저장
                }
            }
        }
        return waitTime;
    }

    class Meet {
        int start;
        int end;

        public Meet(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}