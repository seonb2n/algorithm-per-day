package kotlin


// https://leetcode.com/problems/count-symmetric-integers/?envType=daily-question&envId=2025-04-11
class Solution {
    fun countSymmetricIntegers(low: Int, high: Int): Int {
        var result = 0

        for (i in low until high + 1) {
            val s = i.toString()
            val len = s.length
            if (len % 2 != 0) {
                continue
            }
            var left = 0
            for (j in 0 until len / 2) {
                left += s[j].digitToInt()
            }
            var right = 0
            for (j in len / 2 until len) {
                right += s[j].digitToInt()
            }
            if (left == right) {
                result++
            }
        }
        return result
    }
}
