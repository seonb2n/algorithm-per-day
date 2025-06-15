package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


// https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/?envType=daily-question&envId=2025-06-15
class Solution {
    fun maxDiff(num: Int): Int {
        val target = num.toString()
        // 앞에서부터 9 가 아닌 숫자 remap
        val max = StringBuilder()
        for (i in target.indices) {
            val now = target[i]
            if (now != '9') {
                for (c in target) {
                    if (c == now) {
                        max.append("9")
                    } else {
                        max.append(c)
                    }
                }
                break
            }
        }
        if (max.isEmpty()) {
            for (i in target.indices) {
                max.append("9")
            }
        }

        val min = StringBuilder()

        // 제일 앞자리가 1인 경우
        if (target[0] == '1') {
            var replaced = false
            for (i in target.indices) {
                val now = target[i]
                if (!replaced && now != '0' && now != '1') {
                    // 이 숫자를 0으로 바꾸기로 결정
                    for (c in target) {
                        min.append(if (c == now) '0' else c)
                    }
                    replaced = true
                    break
                }
            }
            // 만약 1과 0만 있는 경우 (예: 1010) 그대로 유지
            if (!replaced) {
                min.append(target)
            }
        } else {
            // 제일 앞자리가 1이 아닌 경우 - 첫 번째 자리를 1로 바꿈
            val firstDigit = target[0]
            for (c in target) {
                min.append(if (c == firstDigit) '1' else c)
            }
        }

        return max.toString().toInt() - min.toString().toInt()
    }
}
