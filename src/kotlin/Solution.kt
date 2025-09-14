package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/vowel-spellchecker/?envType=daily-question&envId=2025-09-14
class Solution {
    fun spellchecker(wordlist: Array<String>, queries: Array<String>): Array<String> {
        val exactMatch = mutableSetOf<String>()
        val caseInsensitiveMatch = mutableMapOf<String, String>()
        val vowelErrorMatch = mutableMapOf<String, String>()

        for (word in wordlist) {
            // 1. 정확한 매치용
            exactMatch.add(word)

            // 2. 대소문자 무시 매치용 (첫 번째 매치만 저장)
            val lowerWord = word.lowercase()
            if (!caseInsensitiveMatch.containsKey(lowerWord)) {
                caseInsensitiveMatch[lowerWord] = word
            }

            // 3. 모음 오류 매치용 (첫 번째 매치만 저장)
            val vowelPattern = replaceVowelsWithStar(lowerWord)
            if (!vowelErrorMatch.containsKey(vowelPattern)) {
                vowelErrorMatch[vowelPattern] = word
            }
        }

        // 각 쿼리에 대해 우선순위에 따라 매칭
        return queries.map { query ->
            when {
                // 1순위: 정확한 매치
                exactMatch.contains(query) -> query

                // 2순위: 대소문자 무시 매치
                caseInsensitiveMatch.containsKey(query.lowercase()) ->
                    caseInsensitiveMatch[query.lowercase()]!!

                // 3순위: 모음 오류 매치
                vowelErrorMatch.containsKey(replaceVowelsWithStar(query.lowercase())) ->
                    vowelErrorMatch[replaceVowelsWithStar(query.lowercase())]!!

                // 매치 없음
                else -> ""
            }
        }.toTypedArray()
    }

    // 모든 모음을 '*'로 치환하여 패턴 생성
    private fun replaceVowelsWithStar(word: String): String {
        val vowels = setOf('a', 'e', 'i', 'o', 'u')
        return word.map { if (it in vowels) '*' else it }.joinToString("")
    }
}
