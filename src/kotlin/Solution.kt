package kotlin

// https://leetcode.com/problems/fruit-into-baskets/submissions/1722863572/?envType=daily-question&envId=2025-08-04
class Solution {
    fun totalFruit(fruits: IntArray): Int {
        if (fruits.isEmpty()) return 0

        var max = 1
        var last = fruits[0]        // 가장 최근에 본 과일
        var secondLast = -1         // 그 이전 과일
        var lastCount = 1           // 가장 최근 과일이 연속으로 나온 횟수
        var curr = 1                // 현재 유효한 윈도우 길이

        for (i in 1 until fruits.size) {
            val fruit = fruits[i]

            when (fruit) {
                last -> {
                    // 같은 과일이 계속 나오는 경우
                    lastCount++
                    curr++
                }
                secondLast -> {
                    // 이전 과일이 다시 나오는 경우
                    curr++
                    // last와 secondLast를 교체
                    secondLast = last
                    last = fruit
                    lastCount = 1
                }
                else -> {
                    // 완전히 새로운 과일 등장 (3번째 타입)
                    // 윈도우를 lastCount + 1로 리셋
                    curr = lastCount + 1
                    secondLast = last
                    last = fruit
                    lastCount = 1
                }
            }

            max = maxOf(max, curr)
        }

        return max
    }
}
