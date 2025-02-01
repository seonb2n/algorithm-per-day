package kotlin

// https://leetcode.com/problems/special-array-i/?envType=daily-question&envId=2025-02-01
class Solution {
    fun isArraySpecial(nums: IntArray): Boolean {
        // nums 를 순회하면서 짝수, 홀수가 반복

        var isOdd = nums[0] % 2 != 0

        for (i in 1 until nums.size) {
            val now = nums[i]
            // 현재 숫자는 짝수가 되어야 함
            if (isOdd) {
                if (now % 2 == 0) {
                    isOdd = false
                } else {
                    return false
                }
                // 현재 숫자는 홀수가 되어야 함
            } else {
                if (now % 2 != 0) {
                    isOdd = true
                } else {
                    return false
                }
            }
        }
        return true
    }
}
