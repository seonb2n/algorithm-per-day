package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

//https://leetcode.com/problems/word-subsets/?envType=daily-question&envId=2025-01-10
class Solution {
    fun wordSubsets(words1: Array<String>, words2: Array<String>): List<String> {
        // words2 의 모든 str 이 words1 의 subset 인지 판별
        val result: MutableList<String> = mutableListOf()

        val subSetMap: MutableMap<Int, IntArray> = mutableMapOf()
        for (i in words2.indices) {
            val chars = words2[i].toCharArray()
            val counter = IntArray(26)
            for (char in chars) {
                counter[char - 'a']++
            }
            subSetMap[i] = counter
        }

        for (word in words1) {
            val chars = word.toCharArray()
            val counter = IntArray(26)
            for (char in chars) {
                counter[char - 'a']++
            }
            // words2 의 글자들이 counter 에 모두 존재해야 한다.
            var flag = true
            for (key in subSetMap.keys) {
                val subset = subSetMap[key]!!
                for (i in 0..25) {
                    if (subset[i] > counter[i]) {
                        flag = false
                        break
                    }
                }
                if (!flag) break
            }
            if (flag) {
                result.add(word)
            }

        }
        return result
    }
}
