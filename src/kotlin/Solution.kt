package kotlin

// https://leetcode.com/problems/3sum/
class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort() // 배열을 정렬하여 중복 체크를 쉽게 함
        val result = mutableSetOf<List<Int>>() // Set을 사용하여 중복 제거

        for (i in nums.indices) {
            // 같은 첫 번째 숫자는 건너뛰기
            if (i > 0 && nums[i] == nums[i-1]) continue

            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]

                when {
                    sum == 0 -> {
                        result.add(listOf(nums[i], nums[left], nums[right]))
                        left++
                        right--

                        // 중복되는 값 건너뛰기
                        while (left < right && nums[left] == nums[left-1]) left++
                        while (left < right && nums[right] == nums[right+1]) right--
                    }
                    sum < 0 -> left++
                    else -> right--
                }
            }
        }

        return result.toList()
    }
}
