package kotlin

import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt

// https://leetcode.com/problems/divide-array-into-equal-pairs/?envType=daily-question&envId=2025-03-17
class Solution {
    fun divideArray(nums: IntArray): Boolean {
        val numMap = mutableMapOf<Int, Int>()
        for (num in nums) {
            if (numMap.containsKey(num)) {
                numMap[num] = numMap[num]!! + 1
            } else {
                numMap[num] = 1
            }
        }

        for (key in numMap.keys) {
            if (numMap[key]!! % 2 != 0) {
                return false
            }
        }
        return true
    }
}
