package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/submissions/1566510838/?envType=daily-question&envId=2025-03-08
class Solution {
    fun minimumRecolors(blocks: String, k: Int): Int {
        //누적합 sum[i] 는 blocks 의 i 번째 까지 존재하는 white 의 개수
        val n = blocks.length
        val sum = IntArray(n+1)
        for (i in 1..n) {
            if (blocks[i-1] == 'W') {
                sum[i] = sum[i-1] + 1
            } else {
                sum[i] = sum[i-1]
            }
        }

        // sum[i+k] - sum[i] 가 최소가 되는 값을 찾는다
        var result = Int.MAX_VALUE
        var i = 0
        while (i + k < n + 1) {
            result = minOf(result, sum[i+k] - sum[i])
            i++
        }

        return result
    }
}
