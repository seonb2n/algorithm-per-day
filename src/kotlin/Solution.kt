package kotlin

import java.util.*

// https://leetcode.com/problems/count-number-of-bad-pairs/description/?envType=daily-question&envId=2025-02-09
class Solution {
    fun countBadPairs(nums: IntArray): Long {
        // 전체 pair 를 계산
        val totalPair: Long = (nums.size.toLong() * (nums.size - 1)) / 2

        // good pair 의 개수 제거
        var goodPair = 0L

        // good pair 의 개수 세기
        val goodPairMap = mutableMapOf<Int, Long>()

        for (i in nums.indices) {
            val now = nums[i] - i
            val count = goodPairMap.getOrDefault(now, 0) + 1
            goodPair += count - 1
            goodPairMap[now] = count
        }
        return totalPair - goodPair
    }
}
