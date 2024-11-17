import java.util.*;

// leetcode search-in-rotated-sorted-array
class Solution {
    public int search(int[] nums, int target) {
        return divide(nums, target, 0, nums.length - 1);
    }

    public int divide(int[] nums, int target, int left, int right) {
        if (left == right) {
            if (nums[left] == target) {
                return left;
            } else {
                return -1;
            }
        }
        int mid = (left + right) / 2;
        var foundLeft = divide(nums, target, left, mid);
        if (foundLeft != -1) {
            return foundLeft;
        }
        var foundRight = divide(nums, target, mid + 1, right);
        if (foundRight != -1) {
            return foundRight;
        }
        return -1;
    }
}