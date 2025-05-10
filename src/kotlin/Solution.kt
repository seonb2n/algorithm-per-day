package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/?envType=daily-question&envId=2025-05-10
class Solution {
    fun minSum(nums1: IntArray, nums2: IntArray): Long {
        // 0 이 없는 nums 가 기준이 되어야 한다.
        // 0 이 있는 nums 는 최솟값이 정해져야 한다.
        var nums1Sum = 0L
        var nums1ZeroCount = 0
        for (n in nums1) {
            if (n == 0) {
                nums1ZeroCount++
            }
            nums1Sum += n
        }

        var nums2Sum = 0L
        var nums2ZeroCount = 0
        for (n in nums2) {
            if (n == 0) {
                nums2ZeroCount++
            }
            nums2Sum += n
        }

        if (nums1ZeroCount ==0 && nums2ZeroCount == 0 && nums1Sum == nums2Sum) {
            return nums1Sum
        }

        if (nums1ZeroCount == 0 && nums2ZeroCount == 0) {
            return -1
        }

        // 둘 모두 zero 가 있으면
        if (nums1ZeroCount > 0 && nums2ZeroCount > 0) {
            val nums1Min = nums1ZeroCount + nums1Sum
            val nums2Min = nums2ZeroCount + nums2Sum
            return maxOf(nums1Min, nums2Min)
        }
        // 1만 zero 가 있으면
        if (nums1ZeroCount > 0) {
            val nums1Min = nums1ZeroCount + nums1Sum
            if (nums1Min > nums2Sum) {
                return -1
            }
            return nums2Sum
        }
        if (nums2ZeroCount > 0) {
            val nums2Min = nums2ZeroCount + nums2Sum
            if (nums2Min > nums1Sum) {
                return -1
            }
            return nums1Sum
        }

        return -1
    }
}
