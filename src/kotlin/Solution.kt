package kotlin

import java.util.*
import kotlin.math.abs


// https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/?envType=daily-question&envId=2025-07-18
class Solution {
    fun minimumDifference(nums: IntArray): Long {
        val n = nums.size / 3
        // 앞에서부터 n 개를 담음. n + 1 번째부터 큰 순서대로 제거
        val firstPQ = PriorityQueue<Int>(reverseOrder())

        for (i in 0 until n) {
            firstPQ.offer(nums[i])
        }

        // 뒤에서부터 n 개를 담음. n + 1 번빼부터 작은 순서대로 제거
        val secondPQ = PriorityQueue<Int>()
        for (j in nums.size - 1 downTo nums.size - n) {
            secondPQ.offer(nums[j])
        }

        for (i in n until n + n) {
            val now = nums[i]

            if (now <= firstPQ.peek() && secondPQ.peek() <= now) {
                val firstGap = firstPQ.peek() - now
                val secondGap = now - secondPQ.peek()
                if (abs(firstGap) > abs(secondGap)) {
                    firstPQ.poll()
                    firstPQ.offer(now)
                } else {
                    secondPQ.poll()
                    secondPQ.offer(now)
                }
            }
            else if (now <= firstPQ.peek()) {
                firstPQ.poll()
                firstPQ.offer(now)
            }
            else if (secondPQ.peek() <= now) {
                secondPQ.poll()
                secondPQ.offer(now)
            }
            // 이 경우는 now 를 추가하는게 유용하지 않음

        }
        println(firstPQ.peek())
        println(secondPQ.peek())

        var first = 0L
        while (firstPQ.isNotEmpty()) {
            first += firstPQ.poll()
        }
        var second = 0L
        while (secondPQ.isNotEmpty()) {
            second += secondPQ.poll()
        }
        return first - second
    }
}
