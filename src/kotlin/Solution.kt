package kotlin

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.min

// https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/?envType=daily-question&envId=2025-03-10
class Solution {
    fun countOfSubstrings(word: String, k: Int): Long {
        // 모음 표시 및 빈도수 추적을 위한 배열
        val isVowel = BooleanArray(128) { false }
        val frequencies = IntArray(128) { 0 }

        // 모음 표시
        isVowel['a'.code] = true
        isVowel['e'.code] = true
        isVowel['i'.code] = true
        isVowel['o'.code] = true
        isVowel['u'.code] = true

        var response = 0L
        var currentK = 0     // 현재 윈도우 내 자음 개수
        var vowels = 0       // 현재 윈도우 내 서로 다른 모음 종류 수 (최대 5)
        var extraLeft = 0    // 왼쪽으로 추가 확장 가능한 위치 수
        var left = 0         // 왼쪽 포인터

        // 오른쪽 포인터를 이동하며 윈도우 확장
        for (right in word.indices) {
            val rightChar = word[right].code

            // 현재 문자 처리
            if (isVowel[rightChar]) {
                // 모음인 경우
                frequencies[rightChar]++
                if (frequencies[rightChar] == 1) {
                    vowels++ // 처음 등장한 모음이면 카운트 증가
                }
            } else {
                // 자음인 경우
                currentK++
            }

            // 자음이 k개보다 많아진 경우, 왼쪽 포인터 이동
            while (currentK > k) {
                val leftChar = word[left].code
                if (isVowel[leftChar]) {
                    // 모음 제거
                    frequencies[leftChar]--
                    if (frequencies[leftChar] == 0) {
                        vowels-- // 이 모음이 더 이상 없으면 카운트 감소
                    }
                } else {
                    // 자음 제거
                    currentK--
                }
                left++
                extraLeft = 0 // 윈도우 변경으로 extraLeft 초기화
            }

            // 왼쪽으로 확장 가능한 경우 처리 (모음이 중복되어 있는 경우)
            while (vowels == 5 && currentK == k && left < right &&
                isVowel[word[left].code] && frequencies[word[left].code] > 1) {
                extraLeft++
                frequencies[word[left].code]--
                left++
            }

            // 조건 만족 시 결과 증가
            if (currentK == k && vowels == 5) {
                response += (1 + extraLeft)
            }
        }

        return response
    }
}
