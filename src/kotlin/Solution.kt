package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

// https://leetcode.com/problems/finding-3-digit-even-numbers/?envType=daily-question&envId=2025-05-12
class Solution {
    fun findEvenNumbers(digits: IntArray): IntArray {
        // 0~9의 빈도 계산
        val freq = IntArray(10)
        for (digit in digits) {
            freq[digit]++
        }

        // 결과 저장용 TreeSet (정렬 + 중복 제거)
        val result = TreeSet<Int>()

        // 가능한 모든 3자리 수 생성
        for (i in 1..9) { // 첫 번째 자리는 0 불가
            if (freq[i] == 0) continue
            freq[i]--
            for (j in 0..9) { // 두 번째 자리
                if (freq[j] == 0) continue
                freq[j]--
                for (k in 0..8 step 2) { // 세 번째 자리는 짝수
                    if (freq[k] == 0) continue
                    result.add(i * 100 + j * 10 + k)
                }
                freq[j]++
            }
            freq[i]++
        }

        return result.toIntArray()
    }
}
