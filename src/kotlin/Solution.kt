package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/partition-array-according-to-given-pivot/description/?envType=daily-question&envId=2025-03-03
class Solution {
    fun pivotArray(nums: IntArray, pivot: Int): IntArray {
        // queue 사용
        val lessQueue = LinkedList<Int>()
        val equalQueue = LinkedList<Int>()
        val biggerQueue = LinkedList<Int>()

        for (i in nums.indices) {
            if (nums[i] < pivot) {
                lessQueue.add(nums[i])
            } else if (nums[i] > pivot) {
                biggerQueue.add(nums[i])
            }
            else {
                equalQueue.add(nums[i])
            }
        }

        val result = IntArray(nums.size)
        var index = 0
        while (lessQueue.isNotEmpty()) {
            result[index] = lessQueue.poll()
            index++
        }
        while (equalQueue.isNotEmpty()) {
            result[index] = equalQueue.poll()
            index++
        }
        while (biggerQueue.isNotEmpty()) {
            result[index] = biggerQueue.poll()
            index++
        }

        return result
    }
}
