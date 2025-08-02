package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/rearranging-fruits/?envType=daily-question&envId=2025-08-02
class Solution {
    fun minCost(basket1: IntArray, basket2: IntArray): Long {
        // 정렬해서 같으려면 쌍이 맞아야 함
        val totalCount = mutableMapOf<Int, Int>()
        val count1 = mutableMapOf<Int, Int>()
        val count2 = mutableMapOf<Int, Int>()

        for (i in 0 until basket1.size) {
            totalCount[basket1[i]] = totalCount.getOrDefault(basket1[i], 0) + 1
            totalCount[basket2[i]] = totalCount.getOrDefault(basket2[i], 0) + 1

            count1[basket1[i]] = count1.getOrDefault(basket1[i], 0) + 1
            count2[basket2[i]] = count2.getOrDefault(basket2[i], 0) + 1
        }

        for (c in totalCount.values) {
            if (c % 2 == 1) {
                return -1L
            }
        }

        // basket1 에서 옮길 과일
        val target1 = mutableListOf<Int>()
        // basket2 에서 옮길 과일
        val target2 = mutableListOf<Int>()

        for (f in totalCount.keys) {
            val targetCount = totalCount[f]!! / 2
            val current1 = count1.getOrDefault(f, 0)
            val current2 = count2.getOrDefault(f, 0)

            if (current1 > targetCount) {
                for (i in 0 until current1 - targetCount) {
                    target1.add(f)
                }
            } else if (current2 > targetCount) {
                for (i in 0 until current2 - targetCount) {
                    target2.add(f)
                }
            }
        }

        target1.sort()
        target2.sortDescending()

        var result = 0L
        val minFruit = (basket1 + basket2).minOrNull() ?: Int.MAX_VALUE

        for (i in target1.indices) {
            val fruit1 = target1[i]
            val fruit2 = target2[i]

            val directCost = minOf(fruit1, fruit2).toLong()
            val minCost = 2L * minFruit

            result += minOf(directCost, minCost)
        }

        return result
    }
}
