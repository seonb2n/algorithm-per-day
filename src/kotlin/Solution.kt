package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/?envType=daily-question&envId=2025-10-13
class Solution {
    fun removeAnagrams(words: Array<String>): List<String> {
        val wordList = words.toMutableList()

        while (true) {
            var isRemoved = false
            var deleteIndex = -1
            var former = ""


            for (i in wordList.indices) {
                val sortedWord = wordList[i].toCharArray().sorted().joinToString("")
                if (former == sortedWord) {
                    isRemoved = true
                    deleteIndex = i
                    break
                } else {
                    former = sortedWord
                }
            }

            if (isRemoved) {
                wordList.removeAt(deleteIndex)
            } else {
                break
            }
        }

        return wordList
    }
}
