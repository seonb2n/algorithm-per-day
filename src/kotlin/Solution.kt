package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/?envType=daily-question&envId=2025-02-21
class FindElements(root: TreeNode?) {
    private val foundSet = HashSet<Int>()
    init {
        if (root != null) {
            root.`val` = 0
            clean(root)
        }
    }

    private fun clean(now: TreeNode) {
        foundSet.add(now.`val`)

        if (now.left != null) {
            now.left!!.`val` = now.`val` * 2 + 1
            clean(now.left!!)
        }
        if (now.right != null) {
            now.right!!.`val` = now.`val` * 2 + 2
            clean(now.right!!)
        }
    }

    fun find(target: Int): Boolean {
        return foundSet.contains(target)
    }

}
class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
    }
