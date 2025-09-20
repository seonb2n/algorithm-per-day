package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/design-spreadsheet/?envType=daily-question&envId=2025-09-19
class Spreadsheet(rows: Int) {

    val cellMap: MutableMap<String, Int> = mutableMapOf<String, Int>()

    fun setCell(cell: String, value: Int) {
        cellMap[cell] = value
    }

    fun resetCell(cell: String) {
        cellMap[cell] = 0
    }

    fun getValue(formula: String): Int {
        val exp = formula.substring(1)
        val nums = exp.split("+")

        val first = if (nums[0].contains(Regex(".*[A-Z].*"))) cellMap.getOrDefault(nums[0], 0) else nums[0].toInt()
        val second = if (nums[1].contains(Regex(".*[A-Z].*"))) cellMap.getOrDefault(nums[1], 0) else nums[1].toInt()
        return first + second
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
