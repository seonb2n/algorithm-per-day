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

            // 대칭 확인
            if (isSymmetric(i, len)) {
                result++
            }
        }
        return result
    }

    private fun isSymmetric(num: Int, len: Int): Boolean {
        var n = num
        var leftSum = 0
        var rightSum = 0
        val half = len / 2

        // 오른쪽 절반 자릿수 합
        repeat(half) {
            rightSum += n % 10
            n /= 10
        }
        // 왼쪽 절반 자릿수 합
        repeat(half) {
            leftSum += n % 10
            n /= 10
        }

        return leftSum == rightSum
    }
}
