import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// https://leetcode.com/problems/count-days-without-meetings/
class Solution {
    public int countDays(int days, int[][] meetings) {
        int n = meetings.length;
        // 시작 날짜순 정렬
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // 정렬된 배열을 합침
        int result = meetings[0][0] - 1; // 미팅가능한 날은 1 일부터 가장 빠른 시작일까지를 더한 날에서 시작
        int currentEndDay = meetings[0][1]; // 현재 마지막 날
        for (int i = 1; i < n; i++) {
            // 현재 범위보다 시작일이 빠르면
            if (meetings[i][0] > currentEndDay) {
                // 지금까지의 마지막날부터 앞으로의 첫번째 날 까지를 결과에 더해줌
                result += meetings[i][0] - currentEndDay - 1;
            }
            // 현재 범위보다 끝나는 날이 뒤에 있다면 끝나는 날을 업데이트해준다.
            if (currentEndDay < meetings[i][1]) currentEndDay = meetings[i][1];
        }
        // 남은 날이 있다면
        if (days > currentEndDay) {
            result += (days - currentEndDay);
        }
        return result;
    }
}