package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/house-robber-iv/?envType=daily-question&envId=2025-03-15
class Solution {
    fun minCapability(nums: IntArray, k: Int): Int {
        // 이진 탐색

        fun checkCapa(capa: Int): Boolean {
            var count = 0
            var i = 0

            while (i < nums.size) {
                if (nums[i] <= capa) {
                    count++
                    i += 2
                } else {
                    i++
                }
            }
            return count >= k
        }

        var left = 1
        var right = nums.maxOrNull() ?: Int.MAX_VALUE

        while (left < right) {
            val mid = (left + right) / 2

            if (checkCapa(mid)) {
                right = mid
            }
            else {
                left = mid + 1
            }
        }
        return left
    }

}
