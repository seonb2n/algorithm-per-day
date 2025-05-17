package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min


// https://leetcode.com/problems/sort-colors/description/?envType=daily-question&envId=2025-05-17
class Solution {
    fun sortColors(nums: IntArray): Unit {
        val counter = IntArray(3)

        for (n in nums) {
            if (n == 0) {
                counter[0]++
            }
            else if (n == 1) {
                counter[1]++
            }
            else if (n == 2) {
                counter[2]++
            }
        }
        for (i in nums.indices) {
            if (counter[0] > 0) {
                nums[i] = 0
                counter[0]--
            }
            else if (counter[1] > 0) {
                nums[i] = 1
                counter[1]--
            }
            else if (counter[2] > 0) {
                nums[i] = 2
                counter[2]--
            }
        }
    }
}
