package kotlin

import java.util.*

// https://leetcode.com/problems/group-anagrams/
class Solution {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = mutableMapOf<String, MutableList<String>>()

        for (s in strs) {
            val now = s.toCharArray().sorted().joinToString("")
            map.getOrPut(now) { mutableListOf() }.add(s)
        }

        return map.values.toList()
    }
}
