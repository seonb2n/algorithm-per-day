package kotlin

import java.util.*


// https://leetcode.com/problems/push-dominoes/submissions/1623293834/?envType=daily-question&envId=2025-05-02
class Solution {
    fun pushDominoes(dominoes: String): String {
        val n = dominoes.length
        val forces = IntArray(n) // 각 도미노에 가해지는 힘 (양수: 오른쪽, 음수: 왼쪽)
        val result = dominoes.toCharArray()

        // 1. 오른쪽으로 가는 힘('R') 전파
        var force = 0
        for (i in 0 until n) {
            if (dominoes[i] == 'R') {
                force = n // 최대 힘으로 설정
            } else if (dominoes[i] == 'L') {
                force = 0 // 'L'은 오른쪽 힘을 차단
            } else {
                force = maxOf(0, force - 1) // 힘은 감소하지 않음, '.'에서는 유지
            }
            forces[i] += force
        }

        // 2. 왼쪽으로 가는 힘('L') 전파
        force = 0
        for (i in n - 1 downTo 0) {
            if (dominoes[i] == 'L') {
                force = n // 최대 힘으로 설정
            } else if (dominoes[i] == 'R') {
                force = 0 // 'R'은 왼쪽 힘을 차단
            } else {
                force = maxOf(0, force - 1) // 힘은 감소하지 않음
            }
            forces[i] -= force
        }

        // 3. 최종 상태 결정
        for (i in 0 until n) {
            if (forces[i] > 0) {
                result[i] = 'R'
            } else if (forces[i] < 0) {
                result[i] = 'L'
            } else {
                result[i] = '.'
            }
        }

        return result.joinToString("")
    }
}
