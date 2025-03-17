package kotlin

import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt

// https://leetcode.com/problems/divide-array-into-equal-pairs/?envType=daily-question&envId=2025-03-17
class Solution {
    fun divideArray(nums: IntArray): Boolean {
        val pairSet = mutableSetOf<Int>()
        for (num in nums) {
            if (pairSet.contains(num)) {
                pairSet.remove(num)
            } else {
                pairSet.add(num)
            }
        }

        return pairSet.size == 0
    }
}
