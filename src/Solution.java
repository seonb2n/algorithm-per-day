import java.util.*;

class Solution {

    static PriorityQueue<Integer> minQueue = new PriorityQueue<>();
    static PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) {
        String[] sol = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        solution(sol);
    }

    public static int[] solution(String[] operations) {

        for (String operation : operations) {
            if (operation.contains("I")) {

                int number = Integer.parseInt(operation.substring(2));
                minQueue.add(number);
                maxQueue.add(number);
            }
            else if (operation.contains("-")) {
                if (!minQueue.isEmpty()) {
                    int num = minQueue.poll();
                    maxQueue.remove(num);
                }
            }
            else {
                if (!maxQueue.isEmpty()) {
                    int num = maxQueue.poll();
                    minQueue.remove(num);
                }
            }
        }

        int minNum = 0;
        int maxNum = 0;
        if (!minQueue.isEmpty()) {
            minNum = minQueue.poll();
        }
        if (!maxQueue.isEmpty()) {
            maxNum = maxQueue.poll();
        }

        return new int[]{maxNum, minNum};
    }


}