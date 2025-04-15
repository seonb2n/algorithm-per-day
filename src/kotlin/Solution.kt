package kotlin

import kotlin.math.abs

// https://leetcode.com/problems/find-duplicate-subtrees/description/
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
**/
class TreeNode(var `val`: Int) {
     var left: TreeNode? = null
     var right: TreeNode? = null
 }

class Solution {
    fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
        val result = ArrayList<TreeNode?>()
        val nodeMap = mutableMapOf<String, Pair<TreeNode?, Int>>()

        fun dfs(node: TreeNode?): String {
            if (node == null) return "#"
            val left = dfs(node.left)
            val right = dfs(node.right)
            val serialized = "${node.`val`},${left},${right}"
            val current = nodeMap.getOrDefault(serialized, Pair(node, 0))
            nodeMap[serialized] = Pair(node, current.second + 1)

            if (current.second == 1) {
                result.add(node)
            }
            return serialized
        }

        dfs(root)
        return result
    }
}
