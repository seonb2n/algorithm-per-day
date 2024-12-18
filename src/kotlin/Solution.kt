package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.abs

// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
class Solution {
    val map : MutableMap<Int, MutableList<Node>> = HashMap()
    fun verticalTraversal(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        // DFS 로 각 node 별 x,y 값을 계산한다.
        dfs(root, 0, 0)

        // map 에서 key 값이 작은 순서대로 꺼내서, List<List<Int>> 를 만든다.
       return map.toSortedMap()
           .values.map { nodes -> nodes.sorted().map { it.value } }.toMutableList()
    }

    fun dfs(nowNode: TreeNode, x: Int, y: Int) {
        map.getOrPut(y) { mutableListOf() }.add(Node(x, nowNode.`val`))
        if (nowNode.left != null) {
            dfs(nowNode.left!!, x + 1, y - 1)
        }
        if (nowNode.right != null) {
            dfs(nowNode.right!!, x + 1, y + 1)
        }
    }
}

data class Node(val x: Int, val value: Int): Comparable<Node> {
    override fun compareTo(other: Node): Int {
        // x 값이 다르면 x 기준 오름차순
        val xCompare = x.compareTo(other.x)

        // x 값이 같으면 value 기준으로 오름차순
        return if (xCompare != 0) xCompare else value.compareTo(other.value)
    }
}

class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
}
