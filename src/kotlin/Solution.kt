package kotlin

import java.util.*

// https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/?envType=daily-question&envId=2025-04-02
class Solution {
    fun maximumTripletValue(nums: IntArray): Long {
        // 단순 구현
        val n = nums.size
        var max = 0L
        // 시작 구간별 최댓값 구해두기
        val start = IntArray(n)
        start[n-1] = nums[n-1]
        for (i in n-2 downTo 0) {
            start[i] = maxOf(start[i + 1], nums[i])
        }

        for (i in 0 until n-2) {
            for (j in i+1 until n-1) {
                if (nums[i].toLong() - nums[j] < 0) {
                    continue
                }
                max = maxOf(max, (nums[i].toLong() - nums[j]) * start[j+1])
            }
        }
        return max
    }
}
