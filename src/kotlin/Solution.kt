package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min


class Solution {
    fun lengthAfterTransformations(s: String, t: Int, nums: List<Int>): Int {
        val modulo = 1_000_000_007
        val vector = IntArray(26)
        for (c in s) {
            val now = c - 'a'
            vector[now]++
        }

        // 변환행렬
        // 연산 후, j 가 변환 후 i 로 몇개 나오는지
        val matrix = Array(26) { LongArray(26) }
        for (i in 0 until 26) {
            val num = nums[i]
            for (j in 1..num) {
                val nextChar = (i + j) % 26
                matrix[nextChar][i] = 1L
            }
        }

        // 행렬 거듭제곱 계산
        val resultMatrix = matrixPow(matrix, t, modulo)

        var result = 0L
        for (i in 0 until 26) {
            for (j in 0 until 26) {
                result = (result + resultMatrix[i][j] * vector[j]) % modulo
            }
        }
        return result.toInt()
    }

    // 행렬 곱셈
    private fun multiplyMatrix(a: Array<LongArray>, b: Array<LongArray>, modulo: Int): Array<LongArray> {
        val n = a.size
        val result = Array(n) { LongArray(n) }

        for (i in 0 until n) {
            for (j in 0 until n) {
                for (k in 0 until n) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % modulo
                }
            }
        }

        return result
    }

    private fun matrixPow(matrix: Array<LongArray>, power: Int, modulo: Int): Array<LongArray> {
        val n = matrix.size
        var result = Array(n) { LongArray(n) }

        // 단위 행렬로 초기화
        for (i in 0 until n) {
            result[i][i] = 1
        }

        var base = matrix
        var exp = power

        while (exp > 0) {
            if (exp % 2 == 1) {
                result = multiplyMatrix(result, base, modulo)
            }

            base = multiplyMatrix(base, base, modulo)
            exp /= 2
        }

        return result
    }
}
