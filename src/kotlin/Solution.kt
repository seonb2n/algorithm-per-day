package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/?envType=daily-question&envId=2025-02-25
class Solution {
    fun numOfSubarrays(arr: IntArray): Int {
        val MOD = 1_000_000_007
        var result = 0
        var sum = 0L
        var evenCount = 1 // 빈 부분 배열은 짝수 합(0) 처리
        var oddCount = 0

        for (i in arr.indices) {
            sum += arr[i]
            // 현재의 누적합이 짝수라면, 지금까지 존재하던 홀수 누적합의 개수를 더함
            if (sum % 2 == 0L) {
                result = (result + oddCount) % MOD
                evenCount++
            } else {
                result = (result + evenCount) % MOD
                oddCount++
            }
        }
        return result
    }
}
