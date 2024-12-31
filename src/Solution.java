
import java.util.*;

// https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        // 각 숫자별 갯수를 셈
        // 숫자가 적은 순서대로 제거해야 함
        PriorityQueue<Node> q = new PriorityQueue<>();
        Arrays.sort(arr);

        Node n = new Node(arr[0], 1);
        for (int i = 1; i < arr.length; i++) {
            int now = arr[i];
            if (n.number == now) {
                n.count++;
            } else {
                q.offer(n);
                n = new Node(arr[i], 1);
            }
        }
        q.offer(n);

        int cnt = 0;
        int rest = k;
        while(!q.isEmpty()) {
            Node next = q.poll();
            if (rest == 0) {
                cnt++;
            }
            else if (next.count <= rest) {
                rest -= next.count;
            } else {
                rest = 0;
                cnt++;
            }
        }
        return cnt;
    }
}

class Node implements Comparable<Node> {
    int number;
    int count;

    public Node(int n, int c) {
        this.number = n;
        this.count = c;
    }

    @Override
    public int compareTo(Node o) {
        return this.count - o.count;
    }
}
