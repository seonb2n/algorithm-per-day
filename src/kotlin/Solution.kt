package kotlin

// https://leetcode.com/problems/sum-of-all-subset-xor-totals/description/?envType=daily-question&envId=2025-04-05
class Solution {
    fun subsetXORSum(nums: IntArray): Int {
        // backtrack
        val n = nums.size
        val isVisited = BooleanArray(n)

        var result = 0

        fun backtrack(start: Int, now: Int) {
            result += now
            for (i in start until n) {
                if (!isVisited[i]) {
                    isVisited[i] = true
                    backtrack(i + 1, now xor nums[i])
                    isVisited[i] = false
                }
            }
        }

        backtrack(0, 0)

        return result
    }
}
