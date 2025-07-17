package kotlin

import java.util.*


// https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/?envType=daily-question&envId=2025-07-17
class Solution {
    fun maximumLength(nums: IntArray, k: Int): Int {

        // dp[i][j] = i 를 만족하면서 k 로 끝나는 최대 길이
        val dp = Array(k) { IntArray(k) }

        for (num in nums) {
            val remainder = num % k

            for (target in 0 until k) {
                // 이전 원소의 나머지 (prev + remain) % k == target
                val prevRemainder = (target - remainder + k) % k
                dp[target][remainder] = maxOf(dp[target][remainder], dp[target][prevRemainder] + 1)
            }
        }

        var max = 0

        for (i in 0 until k) {
            for (j in 0 until k) {
                max = maxOf(max, dp[i][j])
            }
        }
        return max
    }
}
