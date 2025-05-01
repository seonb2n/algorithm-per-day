package kotlin

import java.util.*

// https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/?envType=daily-question&envId=2025-05-01 }
class Solution {
    fun maxTaskAssign(tasks: IntArray, workers: IntArray, pills: Int, strength: Int): Int {
        // 작업과 작업자를 오름차순으로 정렬합니다.
        tasks.sort()
        workers.sort()

        val n = tasks.size
        val m = workers.size

        // k개의 작업을 완료할 수 있는지 확인하는 함수
        fun canDoKTasks(k: Int): Boolean {
            if (k == 0) return true
            if (k > m) return false

            // 사용 가능한 작업자를 관리하기 위한 멀티셋(TreeMap 사용)
            val availableWorkers = TreeMap<Int, Int>()
            for (workerStrength in workers) {
                availableWorkers.put(workerStrength, availableWorkers.getOrDefault(workerStrength, 0) + 1)
            }

            var pillsLeft = pills // 남은 알약 수

            for (i in k - 1 downTo 0) {
                val taskStrength = tasks[i]
                var assigned = false

                // 1. 알약 없이 작업을 수행할 수 있는 가장 약한 작업자 찾기
                val strongestWorkerKey = availableWorkers.lastKey()

                if (strongestWorkerKey != null && strongestWorkerKey >= taskStrength) {
                    val count = availableWorkers.get(strongestWorkerKey)!!
                    if (count == 1) {
                        availableWorkers.remove(strongestWorkerKey)
                    } else {
                        availableWorkers.put(strongestWorkerKey, count - 1)
                    }
                    assigned = true
                }

                // 2. 알약 없이 할 수 없으면, 알약을 사용해서 할 수 있는지 확인
                if (!assigned && pillsLeft > 0) {
                    val requiredStrength = taskStrength - strength
                    val workerKeyWithPill = availableWorkers.ceilingKey(requiredStrength)

                    if (workerKeyWithPill != null) {
                        val count = availableWorkers.get(workerKeyWithPill)!!
                        if (count == 1) {
                            availableWorkers.remove(workerKeyWithPill)
                        } else {
                            availableWorkers.put(workerKeyWithPill, count - 1)
                        }
                        pillsLeft--
                        assigned = true
                    }
                }

                if (!assigned) {
                    return false
                }
            }

            return true
        }


        // 이진 탐색으로 최대 k 찾기
        var left = 0
        var right = minOf(n, m)
        var result = 0 // 완료 가능한 최대 작업 수

        while (left <= right) {
            val mid = (left + right) / 2
            if (canDoKTasks(mid)) {
                result = mid
                left = mid + 1
            } else {
                right = mid - 1
            }
        }

        return result
    }
}
