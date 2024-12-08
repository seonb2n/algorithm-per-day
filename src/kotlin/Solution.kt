package kotlin



// https://leetcode.com/problems/decode-string/
class Solution {
    fun decodeString(s: String): String {
        val sb = StringBuilder()
        var index = 0
        while (index < s.length) {
            if (s[index].isDigit()) {
                index = dfs(sb, index, s)
            }
            else if (s[index] in 'a'..'z') {
                sb.append(s[index])
            }
            index++
        }

        return sb.toString()
    }

    fun dfs(sb: StringBuilder, startIndex: Int, s: String): Int {
        var index = startIndex

        while (index < s.length) {
            if (s[index].isDigit()) {
                // 숫자 파싱
                val number = StringBuilder()
                while (index < s.length && s[index].isDigit()) {
                    number.append(s[index])
                    index++
                }

                // '[' 건너뛰기
                index++ // skip '['

                // 재귀적으로 내부 문자열 처리
                val innerContent = StringBuilder()
                index = dfs(innerContent, index, s)

                // 반복 처리
                val repeatCount = number.toString().toInt()
                repeat(repeatCount) {
                    sb.append(innerContent)
                }
            } else if (s[index] == ']') {
                return index
            } else {
                // 일반 문자는 직접 추가
                sb.append(s[index])
                index++
            }
        }

        return index
    }
}
