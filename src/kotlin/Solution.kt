package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/description/?envType=daily-question&envId=2025-02-22
class Solution {
    var index = 0

    fun recoverFromPreorder(traversal: String): TreeNode? {
        return dfs(traversal, 0)
    }

    private fun dfs(traversal: String, depth: Int): TreeNode? {
        var current = 0
        var i = index

        while (i < traversal.length && traversal[i] == '-') {
            current++
            i++
        }

        if (current != depth) return null

        // 숫자 값을 파싱
        var num = 0
        while (i < traversal.length && traversal[i].isDigit()) {
            num = num * 10 + (traversal[i] - '0')
            i++
        }

        // 현재 인덱스 업데이트
        index = i

        // 새 노드 생성
        val node = TreeNode(num)

        // 왼쪽 자식 탐색
        node.left = dfs(traversal, depth + 1)

        // 오른쪽 자식 탐색
        node.right = dfs(traversal, depth + 1)

        return node
    }
}
class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
}
