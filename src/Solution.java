import java.util.*;

// leetcode search-insert-position
class Solution {
    public int searchInsert(int[] nums, int target) {
        int result = divide(nums, 0, nums.length-1, target);
        if (result == -1 && nums[nums.length-1] < target) {
            result = nums.length;
        }
        if (result == -1 && nums[0] >= target) {
            result = 0;
        }
        return result;
    }

    public int divide(int[] nums, int left, int right, int target) {
        if (left == right) {
            return -1;
        }
        if (right - left == 1) {
            if (nums[left] < target && target <= nums[right]) {
                return left+1;
            }
            return -1;
        }
        int mid = (left + right) / 2;
        int foundLeft = divide(nums, left, mid, target);
        if (foundLeft != -1) {
            return foundLeft;
        }
        int foundRight = divide(nums, mid, right, target);
        if (foundRight != -1) {
            return foundRight;
        }
        return -1;
    }
}
