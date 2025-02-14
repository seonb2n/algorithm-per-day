package kotlin

import java.util.*
import kotlin.collections.ArrayList

// https://leetcode.com/problems/product-of-the-last-k-numbers/?envType=daily-question&envId=2025-02-14
class ProductOfNumbers() {

    private val numberList = mutableListOf(1)

    fun add(num: Int) {
        if (num == 0) {
            numberList.clear()
            numberList.add(1)
        } else {
            numberList.add(numberList.last() * num)
        }
    }

    fun getProduct(k: Int): Int {
        val n = numberList.size - 1
        if (k > n) return 0
        return numberList[k] / numberList[n-k]
    }

}
