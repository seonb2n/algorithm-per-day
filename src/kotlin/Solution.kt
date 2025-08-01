package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/pascals-triangle/description/?envType=daily-question&envId=2025-08-01
class Solution {
    fun generate(numRows: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        result.add(listOf(1))

        for (i in 1 until numRows) {
            val list = mutableListOf<Int>()
            list.add(1)
            for (j in 1 until i) {
                list.add(result[i-1][j-1] + result[i-1][j])
            }
            list.add(1)
            result.add(list)
        }

        return result
    }
}
