package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/?envType=daily-question&envId=2025-07-19
class Solution {
    fun removeSubfolders(folder: Array<String>): List<String> {
        folder.sort()

        val result = mutableListOf<String>()
        result.add(folder.get(0))

        for (i in 1 until folder.size) {
            if (folder[i].startsWith(result.last() + "/")) {
                continue
            }
            result.add(folder[i])
        }

        return result
    }
}
