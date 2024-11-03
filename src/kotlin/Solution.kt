package kotlin

// https://leetcode.com/problems/letter-combinations-of-a-phone-number
class Solution {
    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()
        val result = mutableListOf<String>()
        val map = mapOf(
            '2' to "abc",
            '3' to "def",
            '4' to "ghi",
            '5' to "jkl",
            '6' to "mno",
            '7' to "pqrs",
            '8' to "tuv",
            '9' to "wxyz"
        )

        //DFS
        fun dfs(digits: String, index: Int, sb: StringBuilder) {
            if (index == digits.length) {
                result.add(sb.toString())
                return
            }
            val nowStr = map[digits[index]]!!
            for (i in nowStr.indices) {
                sb.append(nowStr[i])
                dfs(digits, index + 1, sb)
                sb.deleteCharAt(sb.length - 1)
            }
        }
        val sb: StringBuilder = StringBuilder()
        dfs(digits, 0, sb)

        return result
    }
}
