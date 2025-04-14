package kotlin

import kotlin.math.abs


// https://leetcode.com/problems/count-good-triplets/?envType=daily-question&envId=2025-04-14
class Solution {
    fun countGoodTriplets(arr: IntArray, a: Int, b: Int, c: Int): Int {
        var result = 0
        val n = arr.size

        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (abs(arr[i] - arr[j]) > a) {
                    continue
                }
                for (k in j + 1 until n) {
                    if (abs(arr[j] - arr[k]) <= b && abs(arr[i] - arr[k]) <= c) {
                        result++
                    }
                }
            }
        }

        return result
    }
}
