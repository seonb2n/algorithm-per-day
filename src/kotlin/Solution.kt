package kotlin


// https://leetcode.com/problems/minimum-operations-to-make-array-values-equal-to-k/?envType=daily-question&envId=2025-04-09
class Solution {
    fun minOperations(nums: IntArray, k: Int): Int {
        // nums 의 요소 중에 k 보다 작은게 1개라도 있으면 -1 반환
        val steps = nums.toSet()
        if (steps.any { it < k }) {
            return -1
        }
        var result = 0
        if (!steps.contains(k)) {
            result++
        }
        result += steps.count() -1

        return result
    }
}
