package kotlin

import java.util.*

// https://leetcode.com/problems/design-a-number-container-system/?envType=daily-question&envId=2025-02-08
class NumberContainers() {
    // number 별 index 를 map 으로 기록
    private val indexMap = mutableMapOf<Int, TreeSet<Int>>()
    // index 별 number 를 map 으로 기록
    private val container = mutableMapOf<Int, Int>()

    fun change(index: Int, number: Int) {
        // 기존에 존재하던 숫자를 indexMap 에서 제거
        val before = container[index]
        if (before != null) {
            indexMap[before]?.remove(index)
            // Set이 비어있다면 맵에서 제거
            if (indexMap[before]?.isEmpty() == true) {
                indexMap.remove(before)
            }
        }

        // 새로운 숫자 추가
        container[index] = number
        indexMap.getOrPut(number) { TreeSet() }.add(index)
    }

    fun find(number: Int): Int {
        return indexMap[number]?.first() ?: -1
    }
}
