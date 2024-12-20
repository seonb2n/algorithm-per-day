package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/max-chunks-to-make-sorted/
class Solution {
    fun maxChunksToSorted(arr: IntArray): Int {
        var result = 0
        // 정렬되지 않은 부분마다 잘라야 함
        // n 번째 청크는 0 부터 n 까지 모두 포함되어야 한다
        var startIndex = 0
        val isFound = BooleanArray(arr.size + 1) { false }
        for (i in arr.indices) {
            val now = arr[i]
            isFound[now] = true
            var flag = false
            for (j in startIndex..i) {
                if (!isFound[j]) {
                    flag = true
                    break
                }
            }
            if (flag) {
                continue
            }
            result++
            startIndex = i
        }
        return result
    }
}
