package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/lexicographically-smallest-equivalent-string/?envType=daily-question&envId=2025-06-05
class Solution {
    fun smallestEquivalentString(s1: String, s2: String, baseStr: String): String {
        // union find
        // 각 문자의 부모
        val parent = IntArray(26) { it }

        // 문자가 속한 그룹 찾기
        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }

        // 그룹 합치기
        fun union(x: Int, y: Int) {
            val rootX = find(x)
            val rootY = find(y)
            if (rootX != rootY) {
                if (rootX < rootY) {
                    parent[rootY] = rootX
                } else {
                    parent[rootX] = rootY
                }
            }
        }

        val n = s1.length
        for (i in 0 until n) {
            val char1 = s1[i] - 'a'
            val char2 = s2[i] - 'a'
            union(char1, char2)
        }

        // baseStr
        val result = StringBuilder()
        for (c in baseStr) {
            val root = find(c - 'a')
            result.append('a' + root)
        }
        return result.toString()
    }
}
