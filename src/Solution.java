import java.util.*;

class Solution {

    public static void main(String[] args) {
        int[] stat = {9};
        solution(16, stat, 2);
    }

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;

        // n 개의 아파트에 w 만큼의 기지국을 설치해서 범위를 모두 커버해야 한다.
        // 범위 내 기지국의 개수를 구하는 공식은 거리 / (1+w*2) 이다.
        // 1 부터 station[1] 까지 필요한 개수
//        int end = stations[0] - w-1;
//        if (end == start) answer++;
//        if (start < end) {
//            int width = end-start;
//            answer += (width / (1+w*2));
//            //나누어 떨어지지 않으면 추가적인 설치가 필요하다.
//            if (width % (1+w*2) != 0) {
//                answer++;
//            }
//        }
        int index = 0;
        int start = 1;
        int end = 1;
        while (index < stations.length) {
            end = stations[index] - w - 1;
            if (start == end) {
                answer++;
                start = stations[index] + w + 1;
                index++;
            } else if (start > end) {
                start = stations[index] + w + 1;
                index++;
            } else {
                int width = end - start + 1;
                // 해당 지역 내 설치 최소 설치 개수를 더한다.
                answer += (width / (1 + w * 2));
                //나누어 떨어지지 않으면 추가적인 설치가 필요하다.
                if (width % (1 + w * 2) != 0) {
                    answer++;
                }
                start = stations[index] + w + 1;
                index++;
            }
        }

        // 마지막 개수를 확인한다.
        if (start < n) {
            int width = n - start + 1;
            answer += (width / (1 + w * 2));
            //나누어 떨어지지 않으면 추가적인 설치가 필요하다.
            if (width % (1 + w * 2) != 0) {
                answer++;
            }
        }

        return answer;
    }
}