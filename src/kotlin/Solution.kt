package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
class Solution {
    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE
        if (dividend == Int.MIN_VALUE && divisor == 1) return Int.MIN_VALUE
        // Long으로 변환하여 오버플로우 방지
        var a = Math.abs(dividend.toLong())
        val b = Math.abs(divisor.toLong())

        // 부호 미리 계산
        val isNegative = (dividend > 0) xor (divisor > 0)

        var ans = 0L

        // 비트 연산으로 나눗셈 수행
        for (i in 31 downTo 0) {
            if (a >= (b shl i)) {
                a -= b shl i
                ans += 1L shl i
            }
        }

        // 부호 적용 및 범위 체크
        return when {
            isNegative -> -ans.coerceAtLeast(Int.MIN_VALUE.toLong()).coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
            else -> ans.coerceAtMost(Int.MAX_VALUE.toLong()).toInt()
        }
    }
}
