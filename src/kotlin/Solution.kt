package kotlin

// https://leetcode.com/problems/count-subarrays-with-score-less-than-k/?envType=daily-question&envId=2025-04-28
class Solution {
    fun countSubarrays(nums: IntArray, k: Long): Long {
        // 누적합으로 sum 구해두기
        val n = nums.size
        val sums = LongArray(n)
        sums[0] = nums[0].toLong()
        for (i in 1 until n) {
            sums[i] = sums[i - 1] + nums[i].toLong()
        }
        var result = 0L
        if (sums[0] < k) {
            result++
        }
        for (i in n-1 downTo 1) {
            val now = sums[i] * (i + 1)
            if (now < k) {
                result += i.toLong()
                break
            }
        }

        for (i in 1 until n) {
            for (j in n-1 downTo i) {
                val now = (sums[j] - sums[i-1]) * (j - i + 1)
                if (now < k) {
                    result += (j - i + 1).toLong()
                    break
                }
            }
        }



        return result
    }
}
