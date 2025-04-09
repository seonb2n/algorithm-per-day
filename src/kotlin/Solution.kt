package kotlin


// https://leetcode.com/problems/sum-root-to-leaf-numbers/
class Solution {
    fun sumNumbers(root: TreeNode?): Int {
        //dfs
        var result = 0

        if (root == null) {
            return 0
        }

        fun dfs(now: Int, node: TreeNode) {
            val next = (now * 10 + node.`val`)
            if (node.left == null && node.right == null) {
                result += next
            }
            if (node.left != null) {
                dfs(next, node.left)
            }
            if (node.right != null) {
                dfs(next, node.right)
            }
        }

        dfs(0, root)

        return result
    }
}
