package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k/?envType=daily-question&envId=2025-11-29
class Solution {
    fun minOperations(nums: IntArray, k: Int): Int {
        val sum = nums.sum()

        val rest = sum % k
        return rest
    }
}
