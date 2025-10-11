package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/maximum-total-damage-with-spell-casting/?envType=daily-question&envId=2025-10-11
import java.util.*

class Solution {
    fun maximumTotalDamage(power: IntArray): Long {
        if (power.isEmpty()) return 0L

        val counts = power.toList().groupingBy { it }.eachCount()
        val uniquePowers = counts.keys.sorted()

        val n = uniquePowers.size
        if (n == 0) return 0L

        // DP 배열 정의: 크기는 고유 피해량의 개수 (N)
        // dp[j]: uniquePowers[j]까지 고려했을 때의 최대 피해량 (Long 타입으로 정의)
        val dp = LongArray(n) { 0L }

        for (j in 0 until n) {
            val currentPower = uniquePowers[j]
            // 피해량 i를 모두 선택했을 때의 총 피해량 (Long으로 형변환 필요)
            val damageOfJ = currentPower.toLong() * counts.getValue(currentPower)

            // 경우 1: currentPower를 선택하지 않음
            // dp[j-1]은 바로 이전 고유 피해량까지 얻은 누적 최대값
            val notChoose = if (j > 0) dp[j - 1] else 0L

            // 경우 2: currentPower를 모두 선택함 (i-1, i-2가 금지되므로 i-3 이하에서 가져옴)

            // 찾아야 할 타겟 값: currentPower - 3
            val target = currentPower - 3
            var prevMax = 0L
            var prevIndex = -1 // target 이하의 값을 가진 최대 인덱스

            // 이진 탐색으로 uniquePowers[k] <= target 인 최대 k를 찾음
            var low = 0
            var high = j - 1 // 현재 인덱스 j 이전까지만 탐색

            while (low <= high) {
                val mid = low + (high - low) / 2
                if (uniquePowers[mid] <= target) {
                    // 조건을 만족함. 현재 인덱스(mid)를 저장하고 더 큰 인덱스(오른쪽)를 찾아봄
                    prevIndex = mid
                    low = mid + 1
                } else {
                    // target보다 큼. 더 작은 인덱스(왼쪽)를 탐색
                    high = mid - 1
                }
            }

            if (prevIndex != -1) {
                prevMax = dp[prevIndex]
            }

            val choose = prevMax + damageOfJ

            dp[j] = maxOf(notChoose, choose)
        }

        return dp[n - 1]
    }
}
