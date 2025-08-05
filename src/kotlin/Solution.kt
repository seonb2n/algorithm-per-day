package kotlin

import java.util.*

// https://leetcode.com/problems/fruits-into-baskets-ii/?envType=daily-question&envId=2025-08-05
class Solution {
    fun numOfUnplacedFruits(fruits: IntArray, baskets: IntArray): Int {
        var counter = 0

        val n = baskets.size
        val isUsed = BooleanArray(n)

        for (fruit in fruits) {
            var placed = false
            for (i in baskets.indices) {
                if (fruit <= baskets[i] && !isUsed[i]) {
                    isUsed[i] = true
                    placed = true
                    break
                }
            }
            if (!placed) {
                counter++
            }
        }

        return counter
    }
}
