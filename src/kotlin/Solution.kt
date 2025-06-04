package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.max
import kotlin.math.min


// https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/?envType=daily-question&envId=2025-06-04
class Solution {
    fun answerString(word: String, numFriends: Int): String {
        // 가능한 모든 조합을 생성
        val sets = mutableSetOf<String>()
        val n = word.length
        // 조합중 lexicoqraphically largest 찾기
        fun findAllSplits(positions: MutableList<Int>, start: Int, remain: Int) {
            if (remain == 0) {
                // 실제 문자열 나누기
                val splits = mutableListOf<String>()
                var prev = 0
                for (pos in positions) {
                    splits.add(word.substring(prev, pos))
                    prev = pos
                }
                splits.add(word.substring(prev))
                sets.addAll(splits)
                return
            }
            // 현재 위치부터 자르기 시도
            for (i in start..n - remain) {
                positions.add(i)
                findAllSplits(positions, i + 1, remain - 1)
                positions.removeLast()
            }
        }

        findAllSplits(mutableListOf(), 1, numFriends - 1)

        return sets.maxOrNull() ?: ""
    }
}
