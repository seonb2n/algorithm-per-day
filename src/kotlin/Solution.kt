package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/find-unique-binary-string/description/?envType=daily-question&envId=2025-02-20
class Solution {
    fun findDifferentBinaryString(nums: Array<String>): String {
        val sorted = nums.sorted()
        var min = 0
        for (num in sorted) {
            val now = num.toInt(2)
            if (min == now) {
                min++
            }
            else if (min < now) {
                return min.toString(2).padStart(nums.size, '0')
            }
        }
        return min.toString(2).padStart(nums.size, '0')
    }
}
