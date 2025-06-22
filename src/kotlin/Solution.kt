package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/?envType=daily-question&envId=2025-06-22
class Solution {
    fun divideString(s: String, k: Int, fill: Char): Array<String> {
        val result = ArrayList<String>()
        val sb = StringBuilder()
        for (c in s) {
            if (sb.length == k) {
                result.add(sb.toString())
                sb.clear()
            }
            sb.append(c)
        }
        if (sb.isNotEmpty()) {
            while (sb.length < k) {
                sb.append(fill)
            }
            result.add(sb.toString())
        }

        return result.toTypedArray()
    }
}
