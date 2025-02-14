package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/product-of-the-last-k-numbers/?envType=daily-question&envId=2025-02-14
class ProductOfNumbers() {

    private val numberList: ArrayList<Int> = ArrayList()

    fun add(num: Int) {
        for (i in numberList.indices) {
            numberList[i] = numberList[i] * num
        }
        numberList.add(num)
    }

    fun getProduct(k: Int): Int {
        return numberList[numberList.size - k]
    }

}
