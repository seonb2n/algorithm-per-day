package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

// https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/?envType=daily-question&envId=2025-06-14
class Solution {
    fun minMaxDifference(num: Int): Int {
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
        // max 가 비었다면 전체 9 로 구성됐으니 길이만큼 8 로 치환
        if (max.isEmpty()) {
            for (i in target.indices) {
                max.append("9")
            }
        }

        // 앞에서부터 0 이 아닌 숫자 remap
        val min = StringBuilder()
        for (i in target.indices) {
            val now = target[i]
            if (now != '0') {
                for (c in target) {
                    if (c == now) {
                        min.append("0")
                    } else {
                        min.append(c)
                    }
                }
                break
            }
        }

        println(max.toString().toInt())
        println(min.toString().toInt())
        return max.toString().toInt() - min.toString().toInt()
    }
}
