package kotlin

// https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/?envType=daily-question&envId=2025-04-04
class Solution {
    fun lcaDeepestLeaves(root: TreeNode?): TreeNode? {
        // dfs
        fun dfs(node: TreeNode?): Result {
            if (node == null) return Result(null, 0)

            // 리프노드
            if (node.left == null && node.right == null) return Result(node, 1)

            val leftResult = dfs(node.left)
            val rightResult = dfs(node.right)

            // 왼쪽과 오른쪽의 깊이가 같으면 현재 노드가 LCA
            if (leftResult.depth == rightResult.depth) {
                return Result(node, leftResult.depth + 1)
            }

            // 다르면 더 깊은쪽을 반환
            return if (leftResult.depth > rightResult.depth) {
                Result(leftResult.node, leftResult.depth + 1)
            } else {
                Result(rightResult.node, rightResult.depth + 1)
            }
        }

        return dfs(root).node
    }

    data class Result(val node: TreeNode?, val depth: Int)
}

class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
    }
