import java.util.*;

//https://leetcode.com/problems/add-two-numbers/
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums1.length; i++) {
            pq.offer(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            pq.offer(nums2[i]);
        }
        int length = pq.size();
        if (length % 2 == 0) {
            for (int i = 0; i < length / 2-1; i++) {
                pq.poll();
            }
            int first = pq.poll();
            int second = pq.poll();
            return ((double)first + (double)second) / 2;
        }
        else {
            for (int i = 0; i < length / 2 - 1; i++) {
                pq.poll();
            }
            return (double) pq.poll();
        }
    }
}
