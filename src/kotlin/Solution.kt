package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/closest-prime-numbers-in-range/?envType=daily-question&envId=2025-03-07
class Solution {
    fun closestPrimes(left: Int, right: Int): IntArray {
        val primes = findPrimesBetween(left, right)
        if (primes.size < 2) {
            return intArrayOf(-1, -1)
        }

        var minDiff = Int.MAX_VALUE
        var result = intArrayOf(-1, -1)

        for (i in 0 until primes.size - 1) {
            val diff = primes[i + 1] - primes[i]
            if (diff < minDiff) {
                minDiff = diff
                result = intArrayOf(primes[i], primes[i + 1])
            }
        }

        return result
    }

    private fun findPrimesBetween(left: Int, right: Int): List<Int> {
        val primes = mutableListOf<Int>()

        for (num in left..right) {
            if (isPrime(num)) {
                primes.add(num)
            }
        }

        return primes
    }

    private fun isPrime(num: Int): Boolean {
        if (num <= 1) return false
        if (num <= 3) return true
        if (num % 2 == 0 || num % 3 == 0) return false

        val sqrt = Math.sqrt(num.toDouble()).toInt()

        for (i in 3..sqrt step 2) {
            if (num % i == 0) return false
        }
        return true
    }
}
