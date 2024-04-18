import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.util.Collections.swap;

public class Main {

    public static void main(String[] args) {
        int[][] delays = {
                {2423, 10},
                {3423, 30},
                {1, 40},
                {450, 50},
                {1200, 60},
                {2781, 100}
        };
        int[] limits = {2, 1};
        Arrays.stream(solution(delays, limits)).forEach(System.out::print);
    }

    static int[] solution(int[][] delays, int[] limits) {
        int maxUser = 0;
        int maxServer = 1;
        for (int i = 0; i < delays[0].length; i++) {
            Integer[] serverDelays = new Integer[delays.length];
            for (int j = 0; j < delays.length; j++) {
                serverDelays[j] = delays[j][i];
            }

            Arrays.sort(serverDelays, Collections.reverseOrder());
            // serverDelays 중에 최고 길이를 찾는다.
            for (int j = 0; j < serverDelays.length; j++) {
                int maxNum = dfs(j, j+1, serverDelays, limits[0], limits[1] * 1000);
                if (maxUser < maxNum) {
                    maxUser = maxNum;
                    maxServer = i+1;
                }
            }
        }
        return new int[]{maxUser, maxServer};
    }

    static int dfs(int maxUser, int nowNumber, Integer[] serverDelays, int limitOne, int limitTwo) {
        if (nowNumber > serverDelays.length - 1) {
            return nowNumber - maxUser + 1;
        }
        int nowUser = serverDelays[nowNumber];
        // nowUser 가 조건에 맞는지 검사해야 함
        if (serverDelays[maxUser] < nowUser * limitOne && serverDelays[maxUser] < nowUser + limitTwo) {
            return dfs(maxUser, nowNumber + 1, serverDelays, limitOne, limitTwo);
        }
        return nowNumber - maxUser;
    }

}
