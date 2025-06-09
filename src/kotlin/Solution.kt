package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/?envType=daily-question&envId=2025-06-09
class Solution {
    fun findKthNumber(n: Int, k: Int): Int {
        val result = mutableListOf<Int>()


        fun dfs(now: Int) {
            result.add(now)
            if (result.size > k) {
                return
            }
            val next = now * 10
            if (next <= n) {
                dfs(next)
            }
            // 자릿수가 바뀌기 전까지 더함
            if (now % 10 == 0)  {
                val limit = (now / 10 + 1) * 10
                for (i in 1 until 10) {
                    val plus = now + i
                    if (plus > limit) {
                        break
                    }
                    if (plus <= n) {
                        dfs(plus)
                    } else {
                        break
                    }
                }
            }
        }

        for (i in 1 until 10) {
            dfs(i)
        }

        return result.get(k-1)
    }
}

