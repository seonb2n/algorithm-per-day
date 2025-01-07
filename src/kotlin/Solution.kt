package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

//https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/description/?envType=daily-question&envId=2025-01-06
class Solution {
    fun minOperations(boxes: String): IntArray {
        val res = IntArray(boxes.length)

        // 왼쪽에서 오른쪽으로 스캔
        // 1 의 갯수
        var one = 0
        // 1 을 현재 위치로 가져오기 위한 움직임
        var opr = 0
        for (i in boxes.indices) {
            res[i] += opr
            if (boxes[i] == '1') {
                one++
            }
            // 다음 위치는, 이전까지의 모든 이동 + 현재까지의 one 을 한칸 더 이동
            opr += one
        }

        one = 0
        opr = 0
        for (i in boxes.length-1 downTo 0) {
            res[i] += opr
            if (boxes[i] == '1') {
                one++
            }
            opr += one
        }

        return res
    }
}
