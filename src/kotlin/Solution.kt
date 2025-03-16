package kotlin

import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt

// https://leetcode.com/problems/minimum-time-to-repair-cars/description/?envType=daily-question&envId=2025-03-16
class Solution {
    fun repairCars(ranks: IntArray, cars: Int): Long {
        fun canRepair(minutes: Long): Boolean {
            var count = 0L
            for (r in ranks) {
                count += floor(sqrt(minutes.toDouble() / r)).toLong()

                if (count >= cars) {
                    return true
                }
            }
            return count >= cars
        }

        var left = 1L
        var right = (ranks.minOrNull()?.toLong() ?: 1L) * (cars.toLong() * cars.toLong())

        while (left < right) {
            val mid = (left + right) / 2
            if (canRepair(mid)) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
}
