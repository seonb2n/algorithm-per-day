import java.util.*;

class Solution {

    public int solution(int[] food_times, long k) {
        Queue<Node> foodNode = new LinkedList<>();

        for (int i = 0; i < food_times.length; i++) {
            foodNode.add(new Node(i+1, food_times[i]));
        }

        long nowTime = 0;

        while (nowTime < k && !foodNode.isEmpty()) {
            Node nowNode = foodNode.poll();
            if (nowNode.food > 0) {
                nowNode.food--;
                nowTime++;
                if (nowNode.food > 0) {
                    foodNode.offer(nowNode);
                }
            }
        }

        if (foodNode.isEmpty()) {
            return -1;
        }

        return foodNode.peek().index;
    }

    class Node {
        int index;
        int food;

        public Node(int index, int food) {
            this.index = index;
            this.food = food;
        }
    }
}
