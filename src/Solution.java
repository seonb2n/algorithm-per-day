import java.util.*;

// leetcode find-first-and-last-position-of-element-in-sorted-array
class Solution {
    static int min;
    static int max;

    public int[] searchRange(int[] nums, int target) {
        min = 100_001;
        max = -1;
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        divide(nums, target, 0, nums.length - 1);

        if (min == 100_001) {
            min = -1;
        }

        return new int[]{min, max};
    }

    public void divide(int[] nums, int target, int left, int right) {
        if (left == right) {
            if (nums[left] == target) {
                min = Math.min(min, left);
                max = Math.max(max, right);
            } else {
                return;
            }
        } else {
            if (nums[left] == target) {
                min = Math.min(min, nums[left]);
            }
            if (nums[right] == target) {
                max = Math.max(max, nums[right]);
            }

            int mid = (left + right) / 2;
            divide(nums, target, left, mid);
            divide(nums, target, mid + 1, right);
        }
    }
}
