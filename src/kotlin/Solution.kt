package kotlin

import java.util.*
import kotlin.math.abs

// https://leetcode.com/problems/delete-duplicate-folders-in-system/?envType=daily-question&envId=2025-07-20
class Solution {
    fun deleteDuplicateFolder(paths: List<List<String>>): List<List<String>> {
        val root = Trie()

        for (path in paths) {
            var current = root
            for (folder in path) {
                current.children.computeIfAbsent(folder) { Trie() }
                current = current.children[folder]!!
            }
        }

        // 노드마다 시그니처 계산
        val signatureToNodes = mutableMapOf<String, MutableList<Trie>>()
        computeSignature(root, signatureToNodes)

        // 중복된 시그니처를 가진 노드 삭제
        for ((signature, nodes) in signatureToNodes) {
            if (nodes.size > 1 && signature != "()") {
                for (node in nodes) {
                    node.deleted = true
                }
            }
        }

        // 삭제되지 않은 경로 수정
        val result = mutableListOf<List<String>>()
        collectValidPaths(root, mutableListOf(), result)
        return result
    }

    private fun computeSignature(node: Trie, signatureToNodes: MutableMap<String, MutableList<Trie>>): String {
        // 리프 노드인 경우
        if (node.children.isEmpty()) {
            node.signature = "()"
            return "()"
        }

        val childSignatures = mutableListOf<String>()
        for ((folderName, childNode) in node.children) {
            val childSig = computeSignature(childNode, signatureToNodes)
            childSignatures.add("$folderName:$childSig")
        }

        childSignatures.sort()
        val signature = "(${childSignatures.joinToString(",")})"
        node.signature = signature

        signatureToNodes.computeIfAbsent(signature) { mutableListOf() }.add(node)
        return signature
    }

    private fun collectValidPaths(node: Trie, currentPath: MutableList<String>, result: MutableList<List<String>>) {
        // 현재 경로가 유효하고 삭제되지 않았으면 결과에 추가
        if (currentPath.isNotEmpty() && !node.deleted) {
            result.add(currentPath.toList())
        }

        // 삭제되지 않은 노드의 자식들만 탐색
        if (!node.deleted) {
            for ((folderName, childNode) in node.children) {
                currentPath.add(folderName)
                collectValidPaths(childNode, currentPath, result)
                currentPath.removeAt(currentPath.size - 1)
            }
        }
    }


    data class Trie(val children: MutableMap<String, Trie> = mutableMapOf(), var signature: String = "", var deleted: Boolean = false)
}
