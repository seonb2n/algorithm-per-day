package kotlin

import java.util.*

// https://leetcode.com/problems/partition-labels/?envType=daily-question&envId=2025-03-30
class Solution {
    fun partitionLabels(s: String): List<Int> {
        // 글자별 위치를 기록해둠
        val result = mutableListOf<Int>()
        val lastMap = mutableMapOf<Char, Int>()

        for (i in s.indices) {
            lastMap[s[i]] = i
        }

        // 탐색
        var start = 0
        var end = 0
        for (i in s.indices) {
            end = maxOf(end, lastMap[s[i]]!!)

            // 현재 인덱스가 end 면 파티션 완성
            if (i == end) {
                result.add(end - start + 1)
                start = end + 1
            }
        }

        return result
    }
}
