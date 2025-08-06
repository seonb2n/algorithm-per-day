package kotlin

import java.util.*

// https://leetcode.com/problems/fruits-into-baskets-iii/?envType=daily-question&envId=2025-08-06
class Solution {
    fun numOfUnplacedFruits(fruits: IntArray, baskets: IntArray): Int {
        val capacityMap = TreeMap<Int, PriorityQueue<Int>>()

        for (i in baskets.indices) {
            capacityMap.computeIfAbsent(baskets[i]) { PriorityQueue<Int>() }.offer(i)
        }

        var counter = 0

        for (f in fruits) {
            val possible = capacityMap.tailMap(fruit)

            var minIndex = Int.MAX_VALUE
            var min = -1

            for ((capacity, indices) in possible) {
                if (indices.isNotEmpty() && indices.peek() < minIndex) {
                    minIndex = indices.peek()
                    min = capacity
                }
            }

            if (min < Int.MAX_VALUE) {
                capacityMap[min]!!.poll()
            } else {
                counter++
            }
        }

        return counter
    }
}
