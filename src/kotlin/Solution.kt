package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/maximum-total-damage-with-spell-casting/?envType=daily-question&envId=2025-10-11
class Solution {
    fun maximumTotalDamage(power: IntArray): Long {
        if (power.isEmpty()) return 0

        val maxPower = power.maxOrNull() ?: 0
        val counts = IntArray(maxPower + 1) { 0 }

        for (p in power) {
            counts[p]++
        }

        // dp[i]는 피해량 <= i 일 때 최대 피해량
        val dp = LongArray(maxPower + 1) { 0 }

        if (maxPower >= 1) {
            dp[1] = 1L * counts[1]
        }

        for (i in 2..maxPower) {
            // 경우 1: i를 선택하지 않음
            val notChooseI = dp[i - 1]

            // 경우 2: i를 선택함 (i-1과 i-2가 금지되므로 i-3의 최대값에 더함)
            val damageOfI = i * counts[i]

            // i-3 인덱스 처리: 음수 방지 (i=2일 경우 i-3=-1 -> 0, i=3일 경우 i-3=0 -> 0)
            val prevMax = if (i - 3 >= 1) dp[i - 3] else 0

            val chooseI = prevMax + damageOfI

            // 최대값 갱신
            dp[i] = maxOf(notChooseI, chooseI)
        }

        return dp[maxPower]
    }
}
