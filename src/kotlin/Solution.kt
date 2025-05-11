package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/three-consecutive-odds/submissions/1630631884/?envType=daily-question&envId=2025-05-11
class Solution {
    fun threeConsecutiveOdds(arr: IntArray): Boolean {
        var counter = 0
        for (i in arr) {
            if (i % 2 != 0) {
                counter++
                if (counter == 3) {
                    return true
                }
            }
            else {
                counter = 0
            }
        }

        return false
    }
}
