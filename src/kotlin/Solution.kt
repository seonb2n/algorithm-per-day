package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/asteroid-collision/
class Solution {
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val stack = ArrayDeque<Int>()
        for (asteroid in asteroids) {
            if (asteroid > 0) {
                stack.add(asteroid)
                continue
            }

            var broken = false
            while (stack.isNotEmpty() && stack.last() > 0) {
                // 그게 아니면 하나씩 꺼내서 처리
                // 현재 꺼가 크면 stack 에 있는거 제거
                if (stack.last() < abs(asteroid)) {
                    stack.removeLast()
                }
                // 둘이 같으면 부서짐 처리
                else if (stack.last() == abs(asteroid)) {
                    stack.removeLast()
                    broken = true
                    break
                }
                // 스택의 내용이 크면 끝
                else {
                    broken = true
                    break
                }
            }
            // 왼쪽으로 가는 소행성이 stack 에 있는 소행성을 다 부쉈다면
            if (!broken) {
                stack.add(asteroid)
            }
        }
        return stack.toIntArray()
    }
}
