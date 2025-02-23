package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/?envType=daily-question&envId=2025-02-23
class Solution {
    fun constructFromPrePost(preorder: IntArray, postorder: IntArray): TreeNode? {

        if (preorder.isEmpty() || postorder.isEmpty()) return null
        return construct(preorder, postorder, 0, preorder.size - 1, 0, postorder.size - 1)
    }

    private fun construct(
        preorder: IntArray, postorder: IntArray,
        preStart: Int, preEnd: Int, postStart: Int, postEnd: Int
    ): TreeNode? {
        // 유효하지 않은 범위
        if (preStart > preEnd || postStart > postEnd) return null

        // 단일 노드
        if (preStart == preEnd) {
            return TreeNode(preorder[preStart])
        }

        // 현재 서브트리의 루트 노드
        val root = TreeNode(preorder[preStart])

        // 왼쪽 서브트리의 루트
        val leftRoot = preorder[preStart + 1]

        // 후위 순위에서 왼쪽 서브트리 루트 찾기
        var postLeftRoot = postStart
        while (postorder[postLeftRoot] != leftRoot) {
            postLeftRoot++
        }

        // 왼쪽 서브트리의 크기 계산
        val leftSubTreeSize = postLeftRoot - postStart + 1

        // 왼쪽 서브트리 구성
        root.left = construct(preorder, postorder,
            preStart + 1, preStart + leftSubTreeSize,
            postStart, postLeftRoot)

        // 오른쪽 서브트리 구성
        root.right = construct(preorder, postorder,
            preStart + leftSubTreeSize + 1, preEnd, postLeftRoot + 1, postEnd - 1
            )

        return root
    }
}

class TreeNode(var `val`: Int) {
     var left: TreeNode? = null
     var right: TreeNode? = null
}
