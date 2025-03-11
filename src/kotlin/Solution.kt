package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/?envType=daily-question&envId=2025-03-11
class Solution {
    fun numberOfSubstrings(s: String): Int {
        val n = s.length
        var result = 0
        val a_list = mutableListOf<Int>()
        val b_list = mutableListOf<Int>()
        val c_list = mutableListOf<Int>()

        for (i in 0..n-1) {
            when(s[i]) {
                'a' -> a_list.add(i)
                'b' -> b_list.add(i)
                'c' -> c_list.add(i)
            }
        }

        if (a_list.size == 0 || b_list.size == 0 || c_list.size == 0) {
            return 0
        }

        var aCursor = 0
        var bCursor = 0
        var cCursor = 0
        while (aCursor < a_list.size && bCursor < b_list.size && cCursor < c_list.size) {
            val max = maxOf(a_list[aCursor], b_list[bCursor], c_list[cCursor])
            val min = minOf(a_list[aCursor], b_list[bCursor], c_list[cCursor])
            result += (n - max)

            if (min == a_list[aCursor]) { aCursor++ }
            if (min == b_list[bCursor]) { bCursor++ }
            if (min == c_list[cCursor]) { cCursor++ }
        }


        return result
    }
}
