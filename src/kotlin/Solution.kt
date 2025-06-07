package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/?envType=daily-question&envId=2025-06-07
class Solution {
    fun clearStars(s: String): String {
        val pq = PriorityQueue<Pair<Char, Int>>() { a, b ->
            if (a.first != b.first) a.first.compareTo(b.first)
            else b.second.compareTo(a.second)
        }

        val toRemove = mutableSetOf<Int>()

        for (i in s.indices) {
            if (s[i] == '*') {
               val removed = pq.poll()
                toRemove.add(removed.second)
                toRemove.add(i)
            } else {
                pq.add(Pair(s[i], i))
            }
        }
        val sb = StringBuilder()
        for (i in s.indices) {
            if (i !in toRemove) {
                sb.append(s[i])
            }
        }

        return sb.toString()
    }
}
