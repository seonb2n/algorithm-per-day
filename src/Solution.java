import java.util.*;

class Solution {

    //alp, cop 를 i,j 만큼 올리기 위해 필요한 최소 cost
    static int[][] dp;
    static Queue<Point> pointQueue = new LinkedList<>();
    static int targetAlp;
    static int targetCop;

    public static int solution(int alp, int cop, int[][] problems) {
        List<Problem> problemList = new ArrayList<>();
        targetAlp = 0;
        targetCop = 0;

        for (int[] problem : problems) {
            Problem p = new Problem(problem[0], problem[1], problem[2], problem[3], problem[4]);
            problemList.add(p);
            targetAlp = Math.max(targetAlp, p.alpReq);
            targetCop = Math.max(targetCop, p.copReq);
        }

        //기본 문제 추가
        problemList.add(new Problem(0, 0, 1, 0, 1));
        problemList.add(new Problem(0, 0, 0, 1, 1));

        //이미 alp, cop 가 target 을 넘었으면 0 반환
        if (alp >= targetAlp && cop >= targetCop) {
            return 0;
        }

        //alp, cop 중 하나라도 target 을 넘는 경우
        targetAlp = Math.max(targetAlp, alp);
        targetCop = Math.max(targetCop, cop);

        //dp 초기화
        dp = new int[targetAlp + 1][targetCop + 1];
        for (int i = 0; i < targetAlp + 1; i++) {
            Arrays.fill(dp[i], 1_000_000);
        }

        pointQueue.offer(new Point(alp, cop));
        dp[alp][cop] = 0;

        while (!pointQueue.isEmpty()) {
            //현재 alp, cop 를 queue 에서 꺼낸다.
            Point nowPoint = pointQueue.poll();
            int nowAlp = nowPoint.alp;
            int nowCop = nowPoint.cop;

            //현재 alp, cop 로 도달할 수 있는 alp, cop 를 찾고 해당 dp 의 값을 업데이트한다.
            for (Problem p : problemList) {
                if (nowAlp >= p.alpReq && nowCop >= p.copReq) {
                    int nextAlp = nowAlp + p.alpRwd;
                    int nextCop = nowCop + p.copRwd;
                    int nextCost = dp[nowAlp][nowCop] + p.cost;
                    //다음 alp 와 cop 가 범위를 벗어나지 않도록 업데이트한다.
                    nextAlp = Math.min(targetAlp, nextAlp);
                    nextCop = Math.min(targetCop, nextCop);

                    //다음 범위가 target 을 넘은 경우에는 그냥 업데이트만 해준다.
                    if (nextAlp == targetAlp && nextCop == targetCop) {
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], nextCost);
                    }

                    //다음 범위가 업데이트 가능한지를 확인하고 업데이트 된 경우에는 queue 에 추가한다.
                    else if (dp[nextAlp][nextCop] > nextCost) {
                        dp[nextAlp][nextCop] = nextCost;
                        pointQueue.add(new Point(nextAlp, nextCop));
                    }
                }
            }
        }

        return dp[targetAlp][targetCop];
    }

}

class Point {
    int alp;
    int cop;

    public Point(int alp, int cop) {
        this.alp = alp;
        this.cop = cop;
    }
}

class Problem {
    int alpReq;
    int copReq;
    int alpRwd;
    int copRwd;
    int cost;

    public Problem(int alpReq, int copReq, int alpRwd, int copRwd, int cost) {
        this.alpReq = alpReq;
        this.copReq = copReq;
        this.alpRwd = alpRwd;
        this.copRwd = copRwd;
        this.cost = cost;
    }

}