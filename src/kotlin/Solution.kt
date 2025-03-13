package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/zero-array-transformation-ii/submissions/1572507944/
class Solution {
    fun minZeroArray(nums: IntArray, queries: Array<IntArray>): Int {
        val n = nums.size
        var sum = 0
        var k = 0
        // 차분 배열
        val cnt = IntArray(n + 1)

        for (i in 0 until n) {
            // 현재 값과, 현재 위치에 대한 업데이트 결과 값(차분 배열의 값 + 누적합)
            while (sum + cnt[i] < nums[i]) {
                // 끝에 도달했다면 0 만들기 불가
                if (k == queries.size) return -1

                val l = queries[k][0]
                val r = queries[k][1]
                val now = queries[k][2]
                k++

                // 이미 지나가서 업데이트 필요 없음
                if (r < i) continue

                // 차분 배열 업데이트
                cnt[maxOf(l, i)] += now
                cnt[r + 1] -= now
            }
            // 누적합 업데이트
            sum += cnt[i]
        }

        return k
    }
}
