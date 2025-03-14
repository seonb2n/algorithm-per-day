package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/maximum-candies-allocated-to-k-children/?envType=daily-question&envId=2025-03-14
class Solution {
    fun maximumCandies(candies: IntArray, k: Long): Int {
        // candies 를 나누어서 k 명의 children 이 가질 수 있는 최대 개수를 찾아야 한다.

        var sum = 0L
        var right = 0
        for (candy in candies) {
            sum += candy
            right = maxOf(right, candy)
        }
        if (sum < k) {
            return 0
        }

        var left = 1

        // 이진탐색으로 checkDividable 가능한 최대 c 탐색
        while (left <= right) {
            val mid = (left + right) / 2
            if (checkDividable(mid, candies, k)) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return right
    }

    private fun checkDividable(c: Int, candies: IntArray, k: Long): Boolean {
        var pile = 0L
        for (candy in candies) {
            pile += candy / c
            if (pile >= k) {
                break
            }
        }
        return pile >= k
    }
}
