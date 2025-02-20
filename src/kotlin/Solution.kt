package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/find-unique-binary-string/description/?envType=daily-question&envId=2025-02-20
class Solution {
    fun findDifferentBinaryString(nums: Array<String>): String {
        // 칸토어의 대각선 논법 활용
        val result = StringBuilder()
        for (i in nums.indices) {
            if (nums[i][i] == '0') {
                result.append('1')
            } else {
                result.append('0')
            }
        }
        return result.toString()
    }
}
