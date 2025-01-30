package kotlin

// https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/?envType=daily-question&envId=2025-01-30
class Solution {
    fun magnificentSets(n: Int, edges: Array<IntArray>): Int {
       // 각 노드별 이동 가능한 위치 탐색
        val graph = Array(n+1) { mutableListOf<Int>() }
        for (e in edges) {
            graph[e[0]].add(e[1])
            graph[e[1]].add(e[0])
        }

        // 각 노드에 BFS 를 수행
        var maxGroups = -1
        val visited = mutableSetOf<Int>()

        // 각 노드 별로 group 찾기
        for (start in 1..n) {
            if (start in visited) continue
            // 현재 노드의 방문 가능한 노드 탐색
            val visitableNodes = findVisitableNodes(start, graph)
            visited.addAll(visitableNodes)

            var nowMax = -1
            for (node in visitableNodes) {
                val groups = bfs(node, graph, n)
                if (groups == -1) return -1
                nowMax = maxOf(nowMax, groups)
            }

            // 전체 최대 갱신
            // 전체 최대 갱신
            if (maxGroups == -1) maxGroups = nowMax
            else maxGroups += nowMax
        }

        return maxGroups
    }

    // BFS로 노드를 그룹에 할당하고 최대 그룹 수를 반환
    private fun bfs(start: Int, graph: Array<MutableList<Int>>, n: Int): Int {
        val queue = ArrayDeque<Int>()
        val groups = IntArray(n + 1) { -1 }  // 각 노드의 그룹 번호

        queue.add(start)
        groups[start] = 1  // 시작 노드는 1번 그룹
        var maxGroup = 1

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            for (next in graph[current]) {
                if (groups[next] == -1) {
                    // 아직 방문하지 않은 노드
                    groups[next] = groups[current] + 1
                    maxGroup = maxOf(maxGroup, groups[next])
                    queue.add(next)
                } else if (Math.abs(groups[next] - groups[current]) != 1) {
                    // 인접한 노드의 그룹 차이가 1이 아닌 경우
                    return -1
                }
            }
        }

        return maxGroup
    }

    // 연결된 모든 노드 탐색
    private fun findVisitableNodes(start: Int, graph: Array<MutableList<Int>>): Set<Int> {
        val result = mutableSetOf<Int>()
        val queue = ArrayDeque<Int>()

        queue.add(start)
        result.add(start)

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            for (next in graph[current]) {
                if (next !in result) {
                    result.add(next)
                    queue.add(next)
                }
            }
        }

        return result
    }
}
