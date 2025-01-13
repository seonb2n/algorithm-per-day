package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.abs

// https://leetcode.com/problems/minimum-length-of-string-after-operations/?envType=daily-question&envId=2025-01-13
class Solution {
    fun minimumLength(s: String): Int {
        // s 를 기준으로 left 에 1글자, right 에 1글자가 존재해야 함
        val map: MutableMap<Char, MutableList<Int>> = mutableMapOf()
        val arr = s.toCharArray()
        for (c in 0..s.length-1) {
            val now = s[c]
            if (!map.containsKey(now)) {
                map[now] = mutableListOf(c)
            } else {
                map[now]!!.add(c)
            }

            // 3개가 쌓이면 제거
            if (map[now]!!.size == 3) {
                val target = map[s[c]]!!
                arr[target[0]] = '.'
                arr[target[2]] = '.'
                target.removeAt(0)
                target.removeAt(1)
            }
        }

        return arr.filter { it != '.' }.size
    }
}
