import java.util.Arrays;
import java.util.Scanner;

import static java.util.Collections.swap;

public class Main {

    public static void main(String[] args) {

        Arrays.stream(solution(new int[]{2, 3, 1, 2}, new int[]{3, 1, 2, 1, 4, 1})).forEach(System.out::print);

        System.out.println();
        Arrays.stream(solution(new int[]{5, 5, 5}, new int[]{1, 2, 1, 2, 3})).forEach(System.out::print);

        System.out.println();
        Arrays.stream(solution(new int[]{2, 1, 3, 4, 3}, new int[]{2, 2, 2, 2, 5, 5, 5})).forEach(System.out::print);
    }

    static int[] solution(int[] emotions, int[] orders) {
        int[] result = new int[orders.length];

        int[] nowEmotions = Arrays.copyOf(emotions, emotions.length);

        for (int i = 0; i < orders.length; i++) {
            // 물을 주지만, 0 인 경우엔 변동이 없다.
            int nowOrder = orders[i]-1;
            if (nowEmotions[nowOrder] != 0) {
                nowEmotions[nowOrder] = emotions[nowOrder] + 1;
            }
            int nowResult = 0;
            for (int j = 0; j < nowEmotions.length; j++) {
                if (nowEmotions[j] > 1) {
                    nowResult++;
                    nowEmotions[j]--;
                } else if (nowEmotions[j] == 1) {
                    nowEmotions[j]--;
                }
            }
            result[i] = nowResult;
        }

//        Arrays.stream(result).forEach(System.out::println);
        return result;
    }

}
