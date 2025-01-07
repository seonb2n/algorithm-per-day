package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

//https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/description/?envType=daily-question&envId=2025-01-06
class Solution {
    fun minOperations(boxes: String): IntArray {
        val res = IntArray(boxes.length)
        for (i in boxes.indices) {
           if (boxes[i] == '1') {
               for (j in 0..res.size-1) {
                   res[j] += Math.abs(j-i)
               }
           }
        }

        return res
    }
}
