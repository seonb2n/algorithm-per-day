package kotlin


// https://leetcode.com/problems/count-pairs-that-form-a-complete-day-ii/
class Solution {
    fun countCompleteDayPairs(hours: IntArray): Long {
        // set 구성
        val extras = IntArray(24)

        for (hour in hours) {
            extras[hour % 24]++
        }

        var result = extras[0].toLong() * (extras[0] - 1) / 2
        result += extras[12].toLong() * (extras[12] - 1) / 2
        for (i in 1 until 12) {
            result += (extras[i].toLong() * extras[24-i])
        }

        return result
    }
}
