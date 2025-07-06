package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/finding-pairs-with-a-certain-sum/?envType=daily-question&envId=2025-07-06
class FindSumPairs(val nums1: IntArray, val nums2: IntArray) {

    val numMap = HashMap<Int, Int>()
    init {
        for (num in nums2) {
            numMap[num] = numMap.getOrDefault(num, 0) + 1
        }
    }

    fun add(index: Int, `val`: Int) {
        val origin = nums2[index]
        numMap[origin] = numMap[origin]!! - 1

        if (numMap[origin] == 0) {
            numMap.remove(origin)
        }

        val next = origin + `val`
        nums2[index] = next
        numMap[next] = numMap.getOrDefault(next, 0)+ 1
    }

    fun count(tot: Int): Int {
        var result = 0
        for (num in nums1) {
            if (num < tot) {
                result += numMap.getOrDefault(tot - num, 0)
            }
        }
        return result
    }

}
