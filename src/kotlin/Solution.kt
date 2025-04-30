package kotlin

// https://leetcode.com/problems/find-numbers-with-even-number-of-digits/?envType=daily-question&envId=2025-04-30
class Solution {
    fun findNumbers(nums: IntArray): Int {
        var result = 0
        for (n in nums) {
            val str = n.toString()
            if (str.length % 2 == 0) {
                result++
            }
        }

        return result
    }
}
