package kotlin

import kotlin.math.abs

// https://leetcode.com/problems/rabbits-in-forest/?envType=daily-question&envId=2025-04-20
class Solution {
    fun numRabbits(answers: IntArray): Int {
        // 같은 숫자를 언급했으면 최소 n + 1 마리가 있다는 것
        val map = mutableMapOf<Int, Int>()
        var result = 0
        for (a in answers) {
            if (a == 0) {
                result++
            }
            else if (!map.containsKey(a)) {
                map[a] = 1
                result += (a + 1)
            }
            else if (map.containsKey(a)) {
                val found = map.getOrDefault(a, 0)
                if (found < a + 1) {
                    map[a] = found + 1
                }
                else {
                    map[a] = 1
                    result += (a + 1)
                }
            }
        }

        return result
    }
}
