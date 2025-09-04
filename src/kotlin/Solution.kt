package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/find-closest-person/?envType=daily-question&envId=2025-09-04
class Solution {
    fun findClosest(x: Int, y: Int, z: Int): Int {
        val first = abs(x - z)
        val second = abs(y - z)

        if (first == second) return 0
        if (first > second) return 2
        if (first < second) return 1
        return 0
    }
}
