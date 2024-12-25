// https://leetcode.com/problems/permutations=ii

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        List<Node> nowRobot = new ArrayList<>();

        for (int i = 0; i < routes.length; i++) {
            int now = routes[i][0];
            nowRobot.add(new Node(points[now-1][0], points[now-1][1],
                    Arrays.stream(routes[i], 1, routes[i].length).map(n -> n - 1).boxed().collect(Collectors.toList())));
        }

        // 초마다 로봇 위치 체크
        while(true) {
            if (nowRobot.size() == 0) {
                break;
            }

            int[][] board = new int[101][101];
            // nowRobot 에 있는 robot 위치 확인
            for (int i = 0; i < nowRobot.size(); i++) {
                board[nowRobot.get(i).r][nowRobot.get(i).c]++;
                if (board[nowRobot.get(i).r][nowRobot.get(i).c] == 2) {
                    answer++;
                }
            }

            // robot 다음 위치로 이동
            List<Node> nextRobot = new ArrayList<>();
            for (int i = 0; i < nowRobot.size(); i++) {
                Node now = nowRobot.get(i);
                // 다음 목적지가 있으면 해당 목적지로 이동
                int nextTarget = now.target.get(0);
                // 지금 목적지라면 다음 목적지로 변환
                if (points[nextTarget][0] == now.r && points[nextTarget][1] == now.c) {
                    now.target.remove(0);
                }
                // 지금 목적지가 마지막이었으면 pass
                if (now.target.isEmpty()) {
                    continue;
                }

                nextTarget = now.target.get(0);
                // r 이 같으면 c 가 이동
                if (points[nextTarget][0] == now.r) {
                    if (now.c < points[nextTarget][1]) {
                        nextRobot.add(new Node(now.r, now.c + 1, now.target));
                    } else {
                        nextRobot.add(new Node(now.r, now.c - 1, now.target));
                    }
                }
                else {
                    if (now.r < points[nextTarget][0]) {
                        nextRobot.add(new Node(now.r + 1, now.c, now.target));
                    }
                    else {
                        nextRobot.add(new Node(now.r - 1, now.c, now.target));
                    }
                }
            }
            nowRobot = nextRobot;
        }
        return answer;
    }
}

class Node {
    int r;
    int c;
    List<Integer> target;
    public Node(int r, int c, List<Integer> target) {
        this.r = r;
        this.c = c;
        this.target = target;
    }
}
