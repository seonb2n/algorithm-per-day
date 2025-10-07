package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/avoid-flood-in-the-city/?envType=daily-question&envId=2025-10-07
class Solution {
    fun avoidFlood(rains: IntArray): IntArray {
        val ans = IntArray(rains.size)
        // key: 호수 번호, value: 마지막으로 비가 내린 날짜(인덱스)
        val fullLakes = mutableMapOf<Int, Int>()
        // 물을 말릴 수 있는 날들의 인덱스를 오름차순으로 저장
        val dryDays = TreeSet<Int>()

        for (i in rains.indices) {
            val lake = rains[i]

            if (lake == 0) {
                // 비가 오지 않는 날은 건조쿠폰 저장
                dryDays.add(i)
                ans[i] = 1
            } else {
                ans[i] = -1

                // 홍수 위기
                if (fullLakes.containsKey(lake)) {
                    // 이 호수에 마지막으로 비가 온 날(lastRainDay) 이후에 사용 가능한 쿠폰이 있을지
                    val lastRainDay = fullLakes[lake]!!

                    // dryDays에서 lastRainDay보다 크거나 같은 첫 번째 날을 찾습니다.
                    val nextDryDay = dryDays.ceiling(lastRainDay)
                        ?: // 사용 가능한 건조 쿠폰이 없으면 홍수를 막을 수 없습니다.
                        return IntArray(0)

                    ans[nextDryDay] = lake
                    dryDays.remove(nextDryDay)
                    fullLakes[lake] = i
                } else {
                    fullLakes[lake] = i
                }
            }
        }
        return ans
    }
}
