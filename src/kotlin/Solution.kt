package kotlin

// https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/?envType=daily-question&envId=2025-02-03
class Solution {
   fun longestMonotonicSubarray(nums: IntArray): Int {
       var result = 1
       var asc = 1
       var desc = 1
       for (i in 1 until nums.size) {
           if (nums[i] > nums[i-1]) {
               asc++
               desc = 1
           } else if (nums[i] < nums[i-1]) {
               desc++
               asc = 1
           } else {
               asc = 1
               desc = 1
           }
           result = maxOf(result, asc, desc)
       }
       return result
   }
}
