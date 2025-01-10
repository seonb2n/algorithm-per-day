package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

//https://leetcode.com/problems/word-subsets/?envType=daily-question&envId=2025-01-10
class Solution {
    fun wordSubsets(words1: Array<String>, words2: Array<String>): List<String> {
        // words2 의 모든 str 이 words1 의 subset 인지 판별
        val result: MutableList<String> = mutableListOf()

        // words2 의 모든 문자열을 모은 통합 카운터를 만들 수 있다.
        val maxCounter = IntArray(26)
        for (word in words2) {
            val counter = IntArray(26)
            for (char in word) {
                counter[char - 'a']++
            }
            for (i in 0..25) {
                maxCounter[i] = maxOf(maxCounter[i], counter[i])
            }
        }

        for (word in words1) {
            val chars = word.toCharArray()
            val counter = IntArray(26)
            for (char in chars) {
                counter[char - 'a']++
            }
            // words2 의 글자들이 maxCounter 에 모두 존재해야 한다.
            var flag = true
            for (i in 0..25) {
                if (maxCounter[i] > counter[i]) {
                    flag = false
                    break
                }
            }
            if (flag) {
                result.add(word)
            }

        }
        return result
    }
}
