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
        val counter = HashMap<Int, Int>()
        for (i in arr.indices) {
            val now = arr[i]
            counter[now] = counter.getOrDefault(now, 0) + 1
        }

        var result = 0

        for (key in counter.keys) {
            if (key == counter.get(key)) {
                if (result < key) result = key
            }
        }

        return result
    }
}
