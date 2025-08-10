package kotlin

import java.util.*
import kotlin.math.ceil

//https://leetcode.com/problems/reordered-power-of-2/?envType=daily-question&envId=2025-08-10
class Solution {
    fun reorderedPowerOf2(n: Int): Boolean {
        // n 의 각 자릿수 세기
        fun countDigit(target: Int): IntArray {
            val digits = IntArray(10)
            var num = target
            while (num > 0) {
                digits[num % 10]++
                num /= 10
            }
            return digits
        }

        val origin = countDigit(n)

        var counter = 1

        while (counter <= 1000000000) {
            if (countDigit(counter) contentEquals origin) {
                return true
            }
            counter *= 2
        }
        return false
    }
}
