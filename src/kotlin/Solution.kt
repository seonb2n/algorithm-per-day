package kotlin

import java.util.*

// https://leetcode.com/problems/solving-questions-with-brainpower/submissions/1593108016/?envType=daily-question&envId=2025-04-01
class Solution {
    fun mostPoints(questions: Array<IntArray>): Long {
        // dp
        val n = questions.size
        val dp = LongArray(n) { -1L }

        fun getDp(i: Int): Long {
            if (i >= n) {
                return 0L
            }
            if (dp[i] != -1L) {
                return dp[i]
            }

            dp[i] = maxOf(questions[i][0] + getDp(i + questions[i][1] + 1), getDp(i + 1))
            return dp[i]
        }
        return getDp(0)
    }
}
