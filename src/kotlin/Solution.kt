package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/compare-version-numbers/description/?envType=daily-question&envId=2025-09-23
class Solution {
    fun compareVersion(version1: String, version2: String): Int {
        val first = version1.split(".")
        val second = version2.split(".")

        fun compare(index: Int): Int {
            if (index >= first.size && index >= second.size) {
                return 0
            }
            else if (index >= first.size && index < second.size) {
                if (second[index].toInt() > 0) {
                    return -1
                } else {
                    return compare(index + 1)
                }
            }
            else if (index >= second.size && index < first.size) {
                if (first[index].toInt() > 0) {
                    return 1
                } else {
                    return compare(index + 1)
                }
            }
            else {
                if (first[index].toInt() == second[index].toInt()) {
                    return compare(index + 1)
                } else {
                    return if (first[index].toInt() > second[index].toInt()) 1 else -1
                }
            }
        }

        return compare(0)
    }
}
