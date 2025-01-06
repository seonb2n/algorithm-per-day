package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/jump-game-ii/
class Solution {
    fun jump(nums: IntArray): Int {
        if (nums.size <= 1) return 0

        var curMax = 0  // 현재 점프로 갈 수 있는 최대 거리
        var nextMax = 0 // 다음 점프로 갈 수 있는 최대 거리
        var jumps = 0

        for (i in 0 until nums.size - 1) {
            nextMax = maxOf(nextMax, i + nums[i])

            if (i == curMax) {
                jumps++
                curMax = nextMax

                if (curMax >= nums.size - 1) break
            }
        }

        return jumps
    }
}
