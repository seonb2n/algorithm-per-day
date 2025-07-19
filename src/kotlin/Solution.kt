package kotlin

import java.util.*
import kotlin.math.abs


// https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/?envType=daily-question&envId=2025-07-18
class Solution {
    fun minimumDifference(nums: IntArray): Long {
        val n = nums.size / 3

        // 각 위치에서 왼쪽 파트의 최소 합
        val leftMin = LongArray(3 * n)
        // 각 위치에서 오른쪽 파트의 최대 합
        val rightMax = LongArray(3 * n)

        // 왼쪽에서 최소 n개 원소 찾기
        val maxHeap = PriorityQueue<Int>(reverseOrder())
        var sum = 0L

        for (i in 0 until 2 * n) {
            maxHeap.offer(nums[i])
            sum += nums[i]

            if (maxHeap.size > n) {
                sum -= maxHeap.poll()
            }

            if (i >= n - 1) {
                leftMin[i] = sum
            }
        }

        // 오른쪽에서 최대 n개 원소 찾기
        val minHeap = PriorityQueue<Int>()
        sum = 0L

        for (i in 3 * n - 1 downTo n) {
            minHeap.offer(nums[i])
            sum += nums[i]

            if (minHeap.size > n) {
                sum -= minHeap.poll()
            }

            if (i <= 2 * n) {
                rightMax[i] = sum
            }
        }

        var result = Long.MAX_VALUE
        for (i in n - 1 until 2 * n) {
            result = minOf(result, leftMin[i] - rightMax[i + 1])
        }

        return result
    }
}
