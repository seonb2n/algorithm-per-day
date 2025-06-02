package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/candy/?envType=daily-question&envId=2025-06-02
class Solution {
    fun candy(ratings: IntArray): Int {
        val n = ratings.size
        if (n == 0) return 0

        val candies = IntArray(n) { 1 }

        // 왼쪽 -> 오른쪽
        for (i in 1 until n) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i-1] + 1
            }
        }

        // 오른쪽 -> 왼쪽
        for (i in n-2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = maxOf(candies[i], candies[i + 1] + 1)
            }
        }

        return candies.sum()
    }
}
