package kotlin

// https://leetcode.com/problems/4sums
class Solution {
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()
        val result = mutableSetOf<List<Int>>()

        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                var left = j+1
                var right = nums.size-1

                while (left < right) {
                    val nowNumber = nums[i].toLong() + nums[j] + nums[left] + nums[right]
                    if (nowNumber == target.toLong()) {
                        result.add(listOf(nums[i], nums[j], nums[left], nums[right]))
                        left++
                    }
                    else if (nowNumber < target) {
                       left++
                    }
                    else {
                        right--
                    }
                }
            }
        }
        return result.map { it.toMutableList() }.toList()
    }
}
