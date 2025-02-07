package kotlin

// https://leetcode.com/problems/find-the-number-of-distinct-colors-among-the-balls/?envType=daily-question&envId=2025-02-07
class Solution {
    fun queryResults(limit: Int, queries: Array<IntArray>): IntArray {
        val colorCounter = mutableMapOf<Int, Int>() // 각 색상의 등장 횟수
        val ballColors = mutableMapOf<Int, Int>() // 각 공의 현재 색상
        val result = IntArray(queries.size)

        for (i in queries.indices) {
            val ball = queries[i][0]
            val newColor = queries[i][1]

            val currentColor = ballColors[ball]
            if (currentColor != null && currentColor != newColor) {
                // 기존 색상 카운트 감소
                val count = colorCounter[currentColor]!! - 1
                if (count == 0) {
                    colorCounter.remove(currentColor)
                } else {
                    colorCounter[currentColor] = count
                }
                // 새 색상 추가
                colorCounter[newColor] = colorCounter.getOrDefault(newColor, 0) + 1
                ballColors[ball] = newColor
            } else if (currentColor == null) {
                // 처음 칠하는 경우
                colorCounter[newColor] = colorCounter.getOrDefault(newColor, 0) + 1
                ballColors[ball] = newColor
            }
            // currentColor == newColor인 경우는 아무 작업도 필요 없음

            result[i] = colorCounter.size
        }
        return result
    }
}
