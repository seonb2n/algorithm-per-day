package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/design-task-manager/?envType=daily-question&envId=2025-09-18
class TaskManager(tasks: List<List<Int>>) {
    // 전역 우선순위 큐 (priority, taskId) - 최대 힙
    private val globalQueue = PriorityQueue<Pair<Int, Int>> { a, b ->
        if (a.first != b.first) b.first.compareTo(a.first) // 우선순위 내림차순
        else b.second.compareTo(a.second) // taskId 내림차순
    }

    private val taskMap = mutableMapOf<Int, Pair<Int, Int>>()

    init {
        for (task in tasks) {
            val (userId, taskId, priority) = task
            add(userId, taskId, priority)
        }
    }

    fun add(userId: Int, taskId: Int, priority: Int) {
        globalQueue.offer(Pair(priority, taskId))

        taskMap[taskId] = Pair(userId, priority)
    }

    fun edit(taskId: Int, newPriority: Int) {
        val (userId, _) = taskMap[taskId]!!
        taskMap[taskId] = Pair(userId, newPriority)
        // 새로운 우선순위로 전역 큐에 추가 (기존 항목은 lazy deletion으로 처리)
        globalQueue.offer(Pair(newPriority, taskId))
    }

    fun rmv(taskId: Int) {
        taskMap.remove(taskId)
    }

    fun execTop(): Int {
        // 전역 큐의 최상단이 유효한 작업이 될 때까지 정리
        while (globalQueue.isNotEmpty()) {
            val (priority, taskId) = globalQueue.peek()

            // 맵에 존재하고 우선순위가 일치하는지 확인
            val mapEntry = taskMap[taskId]
            if (mapEntry != null && mapEntry.second == priority) {
                // 유효한 작업 발견 - 실행
                globalQueue.poll()
                val userId = mapEntry.first
                taskMap.remove(taskId)
                return userId
            } else {
                // 무효한 작업이므로 큐에서 제거
                globalQueue.poll()
            }
        }

        // 실행할 작업이 없는 경우
        return -1
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * var obj = TaskManager(tasks)
 * obj.add(userId,taskId,priority)
 * obj.edit(taskId,newPriority)
 * obj.rmv(taskId)
 * var param_4 = obj.execTop()
 */
