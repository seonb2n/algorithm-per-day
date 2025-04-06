package kotlin

// https://leetcode.com/problems/largest-divisible-subset/?envType=daily-question&envId=2025-04-06
class Solution {
    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        // 백트래킹
        val n = nums.size
        var result = mutableListOf<Int>()

        nums.sort()

        fun backtrack(now: MutableList<Int>, index: Int) {
            if (now.size > result.size) {
                result.clear()
                result.addAll(now)
            }
            if (n - index <= result.size) {
                return
            }
            var last = 1
            if (now.size != 0) {
                last = now.last()
            }
            for (i in index until n) {
                if (nums.isEmpty() || nums[i] % last == 0) {
                    now.add(nums[i])
                    backtrack(now, i + 1)
                    now.removeLast()
                }
            }
        }

        backtrack(mutableListOf(), 0)

        return result
    }
}
