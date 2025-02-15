package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/find-the-punishment-number-of-an-integer/?envType=daily-question&envId=2025-02-15
class Solution {
    fun punishmentNumber(n: Int): Int {
        var res = 0
        for (i in 1 until n + 1) {
            // multiple number 를 쪼개서 i 로 만들수 있는지 체크
            if (canPartition(i)) {
                res += i * i
            }
        }
        return res
    }

    fun canPartition(target: Int): Boolean {
        val square = target * target
        val numStr = square.toString()

        // 재귀적으로 모든 가능한 분할을 시도
        fun dfs(index: Int, currentSum: Int): Boolean {
            // 기저 사례: 문자열의 끝에 도달했을 때
            if (index == numStr.length) {
                return currentSum == target
            }

            // 현재 위치에서 시작하여 가능한 모든 길이의 부분 문자열을 시도
            for (len in 1..numStr.length - index) {
                val substring = numStr.substring(index, index + len)
                // 첫 자리가 0인 경우 (01, 02 등) 건너뛰기
                if (substring.length > 1 && substring[0] == '0') continue

                val num = substring.toInt()
                // 현재까지의 합이 목표값을 초과하면 더 이상 시도하지 않음
                if (currentSum + num > target) break

                // 다음 위치에서 재귀적으로 시도
                if (dfs(index + len, currentSum + num)) {
                    return true
                }
            }
            return false
        }

        return dfs(0, 0)
    }
}
