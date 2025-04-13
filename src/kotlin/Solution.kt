package kotlin


// https://leetcode.com/problems/count-good-numbers/submissions/1605208137/?envType=daily-question&envId=2025-04-13
class Solution {
    fun countGoodNumbers(n: Long): Int {
        val modulo = 1_000_000_007L
        val even = (n + 1) / 2
        val odd = n / 2

        val result = pow(5L, even, modulo) * pow(4L, odd, modulo) % modulo
        return result.toInt()
    }

    fun pow(a: Long, b:Long, m: Long): Long {
        if (b == 0L) return 1L
        val half = pow(a, b / 2, m)
        val result = half * half % m
        return if (b % 2 == 0L) result else (result * a) % m
    }
}
