package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min



// https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/?envType=daily-question&envId=2025-06-13
class Solution {
    fun minimizeMax(nums: IntArray, p: Int): Int {
        val n = nums.size

        nums.sort()
        // 주어진 min 으로 p 개의 쌍을 만들 수 있는지 체크
        var left = 0
        var right = nums[n - 1] - nums[0]

        fun canFormPairs(mid: Int): Boolean {
            var pairs = 0
            var i = 0
            while (i < n - 1) {
                if (nums[i + 1] - nums[i] <= mid) {
                    pairs++
                    i += 2
                    if (pairs == p) {
                        return true
                    }
                }
                else {
                    i++
                }
            }
            return pairs >= p
        }

        while (left < right) {
            val mid = (left + right) / 2
            // mid 개 만들 수 있는지
            if (canFormPairs(mid)) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return left
    }
}
