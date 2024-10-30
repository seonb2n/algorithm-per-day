package kotlin

// https://leetcode.com/problems/integer-to-roman/
class Solution {
    fun intToRoman(num: Int): String {
        val romanMap: MutableMap<Int, String> = mutableMapOf<Int, String>()
        romanMap.put(1, "I")
        romanMap.put(2, "II")
        romanMap.put(3, "III")
        romanMap.put(4, "IV")
        romanMap.put(5, "V")
        romanMap.put(6, "VI")
        romanMap.put(7, "VII")
        romanMap.put(8, "VIII")
        romanMap.put(9, "IX")
        romanMap.put(10, "X")

        val sb: StringBuilder = StringBuilder()

        var rest = num

        if (rest >= 1000) {
            val mock = rest / 1000
            for (i in 1..mock) {
                sb.append("M")
            }
            rest -= (mock * 1000)
        }
        if (rest >= 900) {
            sb.append("CM")
            rest -= 900
        }
        if (rest >= 500) {
            sb.append("D")
            rest -= 500
        }
        if (rest >= 400) {
            sb.append("CD")
            rest -= 400
        }

        if (rest >= 100) {
            val mock = rest / 100
            for (i in 1..mock) {
                sb.append("C")
            }
            rest -= (mock * 100)
        }
        if (rest >= 90) {
            sb.append("XC")
            rest -= 90
        }
        if (rest >= 50) {
            sb.append("L")
            rest -= 50
        }
        if (rest >= 40) {
            sb.append("XL")
            rest -= 40
        }
        if (rest >= 10) {
            val mock = rest / 10
            for (i in 1..mock) {
                sb.append("X")
            }
            rest -= (10 * mock)
        }
        if (rest > 0) {
            sb.append(romanMap[rest])
        }
        return sb.toString()
    }
}
