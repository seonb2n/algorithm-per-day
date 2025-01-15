package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.abs

// https://leetcode.com/problems/minimize-xor/?envType=daily-question&envId=2025-01-15
class Solution {
    fun minimizeXor(num1: Int, num2: Int): Int {
        val target = Integer.bitCount(num2)

        var current = 0
        var result = 0

        // current xor num1 의 최솟값을 찾아야 함
        for (i in 31 downTo 0) {
            val bit = (num1 and (1 shl i))

            when {
                // num1의 해당 위치가 1이고, 아직 1을 더 넣을 수 있으면
                bit != 0 && current < target -> {
                    result = result or (1 shl i)
                    current++
                }
                // 남은 1을 채워야 하는 경우
                current < target && i < (target - current) -> {
                    result = result or (1 shl i)
                    current++
                }
            }
        }

        return result
    }
}
