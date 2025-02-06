package kotlin

// https://leetcode.com/problems/tuple-with-same-product/description/?envType=daily-question&envId=2025-02-06
class Solution {
    fun tupleSameProduct(nums: IntArray): Int {
        // 임의의 두 수의 곱을 저장
        val multipleMap = HashMap<Int, MutableList<Pair<Int, Int>>>()

        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                multipleMap.computeIfAbsent(nums[i] * nums[j]) { mutableListOf() }.add(Pair(i, j))
            }
        }

        // 계산
        var count = 0
        for (i in multipleMap.keys) {
            val pairs = multipleMap[i]

            for (j in pairs!!.indices) {
                for (k in j + 1 until pairs.size) {
                    val (a, b) = pairs[j]
                    val (c, d) = pairs[k]

                    if (a != c && a != d && b != c && b != d) {
                        count++
                    }
                }
            }
        }

        return count * 8
    }
}
