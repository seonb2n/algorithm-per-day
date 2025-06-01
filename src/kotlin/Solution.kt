package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/distribute-candies-among-children-ii/?envType=daily-question&envId=2025-06-01
class Solution {
    fun distributeCandies(n: Int, limit: Int): Long {
        // 전체 경우 : n + 2 개의 공간에 2개의 막대기를 놓는 것
        var total = combination(n + 2 , 2)

        // 한명이 limit 을 초과하는 경우, 한명에게 이미 limit + 1 을 할당함
        if (n > limit) {
            total -= 3 * combination(n - limit -1 + 2, 2)
        }
        // 두명 limit 초과하는 경우 더함
        if (n > 2 * limit) {
            total += 3 * combination(n - 2 * limit - 2 + 2, 2)
        }
        // 세명 limit 초과 빼기
        if (n > 3 * limit) {
            total -= combination(n - 3 * limit - 3 + 2, 2)
        }

        return total
    }

    fun combination(n: Int, r: Int): Long {
       if (r > n || r < 0) {
           return 0
       }
        if (r == 0 || r == n) {
            return 1
        }
        var result = 1L
        for (i in n downTo n-r + 1) {
            result *= i
        }
        for (i in r downTo 1) {
            result /= i
        }
        return result
    }
}
