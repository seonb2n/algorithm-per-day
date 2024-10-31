package kotlin

// https://leetcode.com/problems/roman-to-integer/
class Solution {
    fun romanToInt(s: String): Int {
        val romanStr = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
        val romanMap: MutableMap<String, Int> = mutableMapOf()
        romanMap.put("M", 1000)
        romanMap.put("CM", 900)
        romanMap.put("D", 500)
        romanMap.put("CD", 400)
        romanMap.put("C", 100)
        romanMap.put("XC", 90)
        romanMap.put("L", 50)
        romanMap.put("XL", 40)
        romanMap.put("X", 10)
        romanMap.put("IX", 9)
        romanMap.put("V", 5)
        romanMap.put("IV", 4)
        romanMap.put("I", 1)

        var result = 0

        var cursor = 0
        var romanCursor = 0

        while (cursor < s.length) {
            if (cursor + 2 <= s.length && s.substring(cursor, cursor+2) == romanStr[romanCursor]) {
                result += romanMap[romanStr[romanCursor]]!!
                cursor+=2
            }
            else if(cursor + 1 <= s.length && s.substring(cursor, cursor+1) == romanStr[romanCursor]) {
                result += romanMap[romanStr[romanCursor]]!!
                cursor +=1
            }
            else {
                romanCursor++
            }
        }

        return result
    }
}
