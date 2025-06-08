package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/lexicographical-numbers/?envType=daily-question&envId=2025-06-08
class Solution {
    fun lexicalOrder(n: Int): List<Int> {
        val result = mutableListOf<Int>()

        if (n < 10) {
            for (i in 1 until n + 1) {
                result.add(i)
            }
            return result
        }

        fun dfs(now: Int) {
            result.add(now)
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

        return result
    }
}

