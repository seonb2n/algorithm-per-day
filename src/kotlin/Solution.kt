package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/minimum-number-of-people-to-teach/submissions/1765842232/?envType=daily-question&envId=2025-09-10
class Solution {
    fun minimumTeachings(n: Int, languages: Array<IntArray>, friendships: Array<IntArray>): Int {
        val userLanguages = languages.map { it.toSet() }

        val cannotCommunicate = mutableListOf<Pair<Int, Int>>()

        for (f in friendships) {
            val user1 = f[0] - 1
            val user2 = f[1] - 1

            if (userLanguages[user1].intersect(userLanguages[user2]).isEmpty()) {
                cannotCommunicate.add(Pair(user1, user2))
            }
        }

        var result = Int.MAX_VALUE

        for (lang in 1..n) {
            // 언어의 가르침이 필요한 사용자
            val needToTeach = mutableSetOf<Int>()

            for ((u1, u2) in cannotCommunicate) {
                if (lang !in userLanguages[u1] && lang !in userLanguages[u2]) {
                    needToTeach.add(u1)
                    needToTeach.add(u2)
                } else if (lang !in userLanguages[u1]) {
                    needToTeach.add(u1)
                } else if (lang !in userLanguages[u2]) {
                    needToTeach.add(u2)
                }
            }

            result = minOf(result, needToTeach.size)
        }
        return if (result == Int.MAX_VALUE) 0 else result
    }
}
