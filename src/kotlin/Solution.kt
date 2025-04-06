package kotlin

// https://leetcode.com/problems/largest-divisible-subset/?envType=daily-question&envId=2025-04-06
class Solution {
    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        //dp
        if (nums.isEmpty()) return emptyList()
        val n = nums.size
        nums.sort()

        // dp[i]는 nums[i]를 마지막 원소로 하는 최대 부분집합의 크기
        val dp = IntArray(n) { 1 }
        // prev[i]는 nums[i]를 마지막 원소로 하는 최대 부분집합에서 이전 원소의 인덱스
        val prev = IntArray(n) { -1 }

        var maxIdx = 0
        for (i in 1 until n) {
            for (j in 0 until i) {
                // nums[i]가 nums[j]로 나누어 떨어지고, 기존 dp[i]보다 dp[j]+1이 크면 업데이트
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1
                    prev[i] = j
                }
            }

            // 최대 크기의 부분집합 갱신
            if (dp[i] > dp[maxIdx]) {
                maxIdx = i
            }
        }

        // 최대 부분집합 재구성
        val result = mutableListOf<Int>()
        var currIdx = maxIdx
        while (currIdx >= 0) {
            result.add(nums[currIdx])
            currIdx = prev[currIdx]
        }

        return result.reversed()
    }
}
