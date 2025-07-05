package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/find-the-k-th-character-in-string-game-i/?envType=daily-question&envId=2025-07-03
class Solution {
    fun findLucky(arr: IntArray): Int {
        val counter = IntArray(501)

        for (num in arr) {
            if (num > 0 && num <= 500) {
                counter[num]++
            }
        }

        for (i in counter.indices.reversed()) {
            if (counter[i] != 0 && counter[i] == i) {
                return i
            }
        }

        return -1
    }
}
