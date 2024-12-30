
import java.util.*;

// https://leetcode.com/problems/maximum-units-on-a-truck/
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // 최대한 큰거를 채우는게 이득
        PriorityQueue<Box> pq = new PriorityQueue<Box>();

        for (int i = 0; i < boxTypes.length; i++) {
            pq.offer(new Box(boxTypes[i][1], boxTypes[i][0]));
        }

        int max = 0;
        int number = 0;
        while(number <= truckSize && !pq.isEmpty()) {
            Box now = pq.poll();
            if (number + now.number <= truckSize) {
                max += (now.number * now.size);
                number += now.number;
            } else {
                int next = truckSize - number;
                max += (next * now.size);
                number += next;
            }
        }

        return max;
    }
}

class Box implements Comparable<Box> {
    int size;
    int number;

    public Box(int size, int number) {
        this.size = size;
        this.number = number;
    }

    @Override
    public int compareTo(Box other) {
        return other.size - this.size;
    }
}
