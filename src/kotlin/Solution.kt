package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/count-elements-with-maximum-frequency/submissions/1779030919/?envType=daily-question&envId=2025-09-22
class Solution {
    fun maxFrequencyElements(nums: IntArray): Int {
        val numberMap = mutableMapOf<Int, Int>()

        for (n in nums) {
            numberMap[n] = numberMap.getOrDefault(n, 0) + 1
        }

        var max = 0
        var counter = 0
        for (key in numberMap.keys) {
            if (numberMap[key]!! > max) {
                counter = numberMap[key]!!
                max = numberMap[key]!!
            }
            else if (numberMap[key]!! == max) {
                counter += numberMap[key]!!
            }
        }

        return counter
    }
}
