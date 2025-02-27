package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/?envType=daily-question&envId=2025-02-27
class Solution {
    fun lenLongestFibSubseq(arr: IntArray): Int {
        // 백트래킹
        val numSet = arr.toSet()
        val n = arr.size
        var maxLength = 0

        for (i in 0 until n - 1) {
            for (j in i + 1 until n) {
                var a = arr[i]
                var b = arr[j]

                var length = 2

                while (numSet.contains(a + b)) {
                    val next = a + b
                    a = b
                    b = next
                    length++
                }

                if (length >= 3) {
                    maxLength = maxOf(maxLength, length)
                }
            }
        }

        return maxLength
    }
}
