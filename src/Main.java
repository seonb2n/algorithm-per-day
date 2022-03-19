import java.util.*;

class Solution {
    HashMap<Integer, Integer> timeMap = new HashMap<>();
    static int[] startTimeArr;
    static int[] endTimeArr;
    static int maxNumber = 0;
    static int minStartTime;
    static int maxEndTime;

    public int solution(String[] lines) {

        int n = lines.length;
        minStartTime = Integer.MAX_VALUE;
        maxEndTime = 0;

        startTimeArr = new int[n];
        endTimeArr = new int[n];

        for(int i = 0; i < lines.length; i++) {
            startTimeArr[i] = getStartTime(lines[i]);
            minStartTime = Math.min(minStartTime, startTimeArr[i]);
            endTimeArr[i] = getEndTime(lines[i]);
            maxEndTime = Math.max(maxEndTime, endTimeArr[i]);
        }

        for(int i = 0; i < n; i++) {
            int time = endTimeArr[i];
            int cnt = 0;
            for(int j = 0; j < n; j++) {
                if(time <= startTimeArr[j] && startTimeArr[j] <= time + 999) {
                    cnt++;
                }
                else if(time <= endTimeArr[j] && endTimeArr[j] <= time + 999) {
                    cnt++;
                }
                else if(startTimeArr[j]<=time &&time+999 <= endTimeArr[j]) {
                    cnt++;
                }
            }
            maxNumber = Math.max(maxNumber, cnt);
        }

        return maxNumber;
    }

    public static int getStartTime(String log) {
        StringTokenizer st = new StringTokenizer(log, " ");
        st.nextToken();
        String time = st.nextToken();
        String millSecond = st.nextToken();
        st = new StringTokenizer(millSecond, "s");
        float milSec = Float.parseFloat(st.nextToken());

        st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        float second = Float.parseFloat(st.nextToken());

        if(second > milSec) {
            second = second * 10000 / 10;
            milSec = milSec * 10000 / 10;
            return hour*10000000 + minute * 100000 + (int)(second-milSec) + 1;
        }
        if(minute == 0) {
            hour = hour-1;
            minute = 59;
            second += 60;
            return hour*10000000 + minute * 100000 + (int)((second-milSec) * 1000f) + 1;
        }
        else {
            minute--;
            second+= 60;
            return hour*10000000 + minute * 100000 + (int)((second-milSec) * 1000f) + 1;
        }
    }

    public static int getEndTime(String log) {
        StringTokenizer st = new StringTokenizer(log, " ");
        st.nextToken();
        String time = st.nextToken();
        st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        float second = Float.parseFloat(st.nextToken());
        return hour*10000000 + minute * 100000 + (int)(second*10000 / 10);
    }
}