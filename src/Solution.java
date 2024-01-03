import java.util.*;

// https://leetcode.com/problems/sliding-window-maximum/
class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.reverseOrder());
        int[] res = new int[nums.length - k + 1];

        int cursor = 0;

        while (cursor < k) {
            pq.add(new Node(nums[cursor], cursor));
            cursor++;
        }

        res[0] = pq.peek().value;

        // 하나씩 추가한다.
        while (cursor < nums.length) {
            pq.add(new Node(nums[cursor], cursor));

            //pq 에서 최대값을 찾는다.
            int max = -10001;
            while (max == -10001 && !pq.isEmpty()) {
                if (pq.peek().index + k <= cursor) {
                    pq.poll();
                }
                else {
                    max = pq.peek().value;
                }
            }
            res[cursor - k + 1] = max;
            cursor++;
        }

        return res;
    }

    class Node implements Comparable<Node> {

        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}