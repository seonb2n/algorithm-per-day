package kotlin

import java.util.*

// https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/?envType=daily-question&envId=2025-03-23
class Solution {
    // 다익스트라
    fun countPaths(n: Int, roads: Array<IntArray>): Int {
        // 문제의 요건
        val MOD = 1_000_000_007

        val nodes = mutableListOf<Node>()
        for (i in 0 until n) {
            nodes.add(Node(i, mutableListOf()))
        }

        for (road in roads) {
            val left = road[0]
            val right = road[1]
            val cost = road[2]
            nodes[left].add(right, cost)
            nodes[right].add(left, cost)
        }

        // 최단 거리 배열과 경로 수 배열
        val dist = LongArray(n) { Long.MAX_VALUE }
        val ways = LongArray(n)
        val visited = BooleanArray(n) // 방문 체크 추가

        // 우선순위 큐를 사용한 다익스트라
        val pq = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
        dist[0] = 0
        ways[0] = 1
        pq.add(Pair(0, 0))

        while (!pq.isEmpty()) {
            val (now, cost) = pq.poll()

            // 이미 해당 위치까지 최단 경로를 구함
            if (dist[now] < cost) continue
            if (visited[now]) continue
            visited[now] = true

            for (next in nodes[now].edges) {
                val nextCost = next.second.toLong() + cost
                // 최단 경로 업데이트
                if (nextCost < dist[next.first]) {
                    dist[next.first] = nextCost
                    ways[next.first] = ways[now]
                    pq.offer(Pair(next.first, nextCost))
                }
                // 동일한 경우
                else if (nextCost == dist[next.first]) {
                    // 경로의 수에 지금위치까지 오는 경로의 가짓수를 더해준다.
                    ways[next.first] = (ways[next.first] + ways[now]) % MOD

                }
            }
        }

        return ways[n - 1].toInt()
    }

    data class Node(
        val index: Int,
        val edges: MutableList<Pair<Int, Int>>
    ) {
        fun add(target: Int, cost: Int) {
            edges.add(Pair(target, cost))
        }
    }
}

