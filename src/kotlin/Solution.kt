package kotlin

class Solution {

    val result = mutableListOf<String>()
    fun generateParenthesis(n: Int): List<String> {
        dfs(n, 0, 0, "")
        return result
    }

    fun dfs(n: Int, left: Int, right: Int, now: String) {
        if (now.length == n * 2) {
            result.add(now)
            return
        }
        if (left < n) {
            dfs(n, left + 1, right, now + '(')
        }
        if (right < n && right < left) {
            dfs(n, left, right + 1, now + ')')
        }
    }
}
