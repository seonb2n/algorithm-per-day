import java.util.*;

class Solution {

    static long sumOne;

    static long sumTwo;

    static Queue<Integer> queueOne = new LinkedList<>();
    static Queue<Integer> queueTwo = new LinkedList<>();


    public static void main(String[] args) {
        int[] queue1 = {1,1,1,8,10,9};
        int[] queue2 = {1,1,1,1,1,1};
        System.out.println(solution(queue1, queue2));
    }

    public static int solution(int[] queue1, int[] queue2) {
        sumOne = 0;
        sumTwo = 0;
        int answer = -2;
        for (int i = 0; i < queue1.length; i++) {
            sumOne += queue1[i];
            sumTwo += queue2[i];

            queueOne.offer(queue1[i]);
            queueTwo.offer(queue2[i]);
        }

        //greedy 알고리즘으로 목표치에 근사하게 값을 만들어준다.
        int count = 0;
        while (sumOne != sumTwo) {
            if(sumOne > sumTwo) {
                var targetNumber = queueOne.poll();
                sumTwo += targetNumber;
                sumOne -= targetNumber;
                queueTwo.offer(targetNumber);
            }
            else {
                var targetNumber = queueTwo.poll();
                sumOne += targetNumber;
                sumTwo -= targetNumber;
                queueOne.offer(targetNumber);
            }
            count++;

            //모든 경우를 다 돌은 경우면 break
            if(count > (queue1.length + queue2.length + 2)) {
                answer = -1;
                return answer;
            }
        }
        answer = count;

        return answer;
    }


}