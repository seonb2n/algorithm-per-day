package kotlin

import java.util.PriorityQueue
import kotlin.math.abs

// https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
class Solution {
    fun alertNames(keyName: Array<String>, keyTime: Array<String>): List<String> {
        val nameMap = HashMap<String, MutableList<Int>>()
        for (i in keyTime.indices) {
            val name = keyName[i]
            val time = timeToNumber(keyTime[i])
            nameMap.getOrPut(name) { mutableListOf() }.add(time)
        }

        val result = mutableListOf<String>()

        // 각 직원별로 확인
        for ((name, times) in nameMap) {
            // 시간 순으로 정렬
            times.sort()

            // 슬라이딩 윈도우로 1시간 내 3번 이상 사용했는지 확인
            for (i in 2 until times.size) {
                // 현재와 i-2번째 시간이 1시간 이내인지 확인
                if (times[i] - times[i-2] <= 60) {
                    result.add(name)
                    break
                }
            }
        }

        return result.sorted()
    }

    private fun timeToNumber(time: String): Int {
        val digits = time.split(":")
        val hour = digits[0].toInt()
        val min = digits[1].toInt()
        return hour * 60 + min
    }
}
