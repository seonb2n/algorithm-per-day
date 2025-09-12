package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/sort-vowels-in-a-string/?envType=daily-question&envId=2025-09-11
class Solution {
    fun sortVowels(s: String): String {
        val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')

        val vowelList = mutableListOf<Char>()
        for (char in s) {
            if (char in vowels) {
                vowelList.add(char)
            }
        }

        vowelList.sort()

        val sb = StringBuilder()
        var vowelIndex = 0

        for (char in s) {
            if (char in vowels) {
                // vowel인 경우 정렬된 리스트에서 순서대로 가져옴
                sb.append(vowelList[vowelIndex])
                vowelIndex++
            } else {
                // consonant인 경우 원래 문자 그대로 유지
                sb.append(char)
            }
        }

        return sb.toString()
    }
}
