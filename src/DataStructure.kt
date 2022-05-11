import java.util.*
import kotlin.collections.ArrayList

class DataStructure {
    //1. 배열
    val array: Array<Int> = arrayOf(1, 2, 3)

    //2. ArrayList
    val arrayList: ArrayList<Int> = arrayListOf(1, 2, 3)

    //2-1. immutable list (수정할 수 없는 불변의 리스트)
    val immutableList = listOf<String>("a", "b", "c")

    //2-2. mutable list (수정할 수 있는 리스트)
    val mutableList = mutableListOf<String>("a", "b", "c")

    //3. 스택
    val stack: Stack<Int> = Stack()

    //4. 큐
    val queue: Queue<Int> = LinkedList()

    //5. 링크드 리스트
    val linkedList: LinkedList<Int> = LinkedList()

    //6. 셋
    val set: Set<String> = setOf("a", "b", "c")

    //6-1. mutable set (값을 수정할 수 있는 셋)
    val mutableSet = mutableSetOf<String>("a", "b", "c")

    //7. 맵
    val map = mapOf<String, Int>("one" to 1, "two" to 2)

    //7-1. mutable map
    val mutableMap = mutableMapOf<String, Int>("one" to 1, "two" to 2)

}

fun main(args: Array<String>) {
}