package kotlin

import java.util.*
import kotlin.collections.HashSet
import kotlin.math.floor
import kotlin.math.sqrt


// https://leetcode.com/problems/insert-delete-getrandom-o1/
class RandomizedSet() {
    val list = ArrayList<Int>()
    val map = HashMap<Int, Int>()

    private fun search(`val`: Int): Boolean {
        return map.containsKey(`val`)
    }

    fun insert(`val`: Int): Boolean {
        if (search(`val`)) return false
        list.add(`val`)
        map[`val`] = list.size - 1
        return true
    }

    fun remove(`val`: Int): Boolean {
        if (!search(`val`)) return false
        val index = map[`val`]!!
        val lastElement = list[list.size - 1]
        list[index] = lastElement
        map[lastElement] = index
        list.removeAt(list.size - 1)
        map.remove(`val`)
        return true
    }

    fun getRandom(): Int {
        if (list.isEmpty()) throw NoSuchElementException("Set is empty")
        return list[Random.nextInt(list.size)]
    }

}
