package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/?envType=daily-question&envId=2025-05-27
class Solution {
    fun differenceOfSums(n: Int, m: Int): Int {
        val totalSum = n * (n + 1) / 2
        val k = n / m  // m의 배수 개수
        val multiplesSum = m * k * (k + 1) / 2

        return totalSum - 2 * multiplesSum
    }
}
