import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/72414
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int lastTime = convertToSeconds(play_time);
        int[] perSeconds = new int[lastTime+1];

        for (String log : logs) {
            String[] times = log.split("-");
            int startTime = convertToSeconds(times[0]);
            int endTime = convertToSeconds(times[1]);
            for (int j = startTime; j < endTime; j++) {
                perSeconds[j]++;
            }
        }

        // 0 초부터 광고 계산
        int maxTime = 0;
        long maxValue = 0;

        int lastAdvTime = convertToSeconds(adv_time);

        // 0 초부터 광고 마지막 초까지 누적 시척시간 계산
        for (int i = 0; i < lastAdvTime; i++) {
            maxValue += perSeconds[i];
        }

        long nowValue = maxValue;

        for (int i = lastAdvTime; i < lastTime; i++) {
            nowValue = (nowValue + perSeconds[i] - perSeconds[i-lastAdvTime]);
            if (nowValue > maxValue) {
                maxValue = nowValue;
                maxTime = i - lastAdvTime + 1;
            }
        }

        return convertToTimeString(maxTime);
    }

    public static int convertToSeconds(String timeString) {
        // 시간 문자열을 콜론(:)으로 분리
        String[] parts = timeString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        // 시간을 초 단위로 변환
        int hoursInSeconds = hours * 3600;
        int minutesInSeconds = minutes * 60;

        return hoursInSeconds + minutesInSeconds + seconds;
    }

    public static String convertToTimeString(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}