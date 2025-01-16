package kotlin

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.floor

// https://leetcode.com/problems/minimize-xor/?envType=daily-question&envId=2025-01-15
class Solution {
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        val queue = LinkedList<Node>()
        if (root == null) {
            return emptyList()
        }
        queue.offer(Node(root, 1))
        val result = ArrayList<ArrayList<Int>>()
        while (!queue.isEmpty()) {
            val now = queue.poll()
            if (result.size < now.floor) {
                result.add(ArrayList())
            }
            result.get(now.floor-1).add(now.treeNode.`val`)
            if (now.treeNode.left != null) {
                queue.offer(Node(now.treeNode.left!!, now.floor + 1))
            }
            if (now.treeNode.right != null) {
                queue.offer(Node(now.treeNode.right!!, now.floor + 1))
            }
        }
        return result
    }
}

class Node(val treeNode: TreeNode, val floor: Int)

class TreeNode(var `val`: Int) {

    var left: TreeNode? = null

    var right: TreeNode? = null
}
