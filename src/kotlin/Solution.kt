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

            // 현재 값이 low 보다 작으면, 왼쪽 서브트리도 모두 low 보다 작아서 탐색할 필요 x
            if (now.`val` > low && now.left != null) {
                queue.offer(now.left)
            }
            // 현재 값이 high 보다 크면, 오른쪽 서브트리도 모두 high 보다 커서 탐색할 필요 x
            if (now.`val` < high && now.right != null) {
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
