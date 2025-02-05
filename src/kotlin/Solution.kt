package kotlin

// https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/?envType=daily-question&envId=2025-02-05
class Solution {
    fun areAlmostEqual(s1: String, s2: String): Boolean {
        if (s1.equals(s2)) {
            return true
        }

        var firstDifferent = -1
        var secondDifferent = -1

        var isSwap = false

        for (i in 0 until s1.length) {
            // 아직 swap 을 안했다면 값을 업데이트
            if (s1[i] != s2[i]) {
                if (!isSwap) {
                    if (firstDifferent == -1) {
                        firstDifferent = i
                    } else {
                        secondDifferent = i
                    }
                // 이미 swap 을 했는데 또 다른 값이 나오면
                } else {
                    return false
                }
            }
            // swap 을 안하고 값이 다르면 변경
            if (!isSwap && firstDifferent != -1 && secondDifferent != -1) {
                if (s1[firstDifferent] == s2[secondDifferent] && s1[secondDifferent] == s2[firstDifferent]) {
                    isSwap = true
                    // swap 해서 같아지지 않는다면
                } else {
                    return false
                }
            }
        }
        return isSwap
    }
}
