package kotlin

import java.util.*

// https://leetcode.com/problems/range-sum-of-bst/
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun rangeSumBST(root: TreeNode?, low: Int, high: Int): Int {
        var result = 0
        val queue = LinkedList<TreeNode>()
        queue.offer(root)
        // root 부터 BFS 로 탐색
        while (queue.isNotEmpty()) {
            val now = queue.poll()
            // 숫자가 low 부터 high 사이면 결과에 삽입
            if (now.`val` in low..high) {
                result += now.`val`
            }
            if (now.left != null) {
                queue.offer(now.left)
            }
            if (now.right != null) {
                queue.offer(now.right)
            }
        }

        return result
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
