package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/design-a-food-rating-system/?envType=daily-question&envId=2025-09-17
class FoodRatings(foods: Array<String>, cuisines: Array<String>, ratings: IntArray) {

    // cuisine 별 음식 정렬
    private val cuisineToMaxHeap = mutableMapOf<String, PriorityQueue<Food>>()
    // 음식 rating
    private val foodToRating = mutableMapOf<String, Int>()
    // 음식이 속한 cuisine
    private val foodToCuisine = mutableMapOf<String, String>()

    // 음식 데이터 클래스 (평점 높은 순, 같으면 사전순으로 정렬)
    data class Food(val name: String, val rating: Int) : Comparable<Food> {
        override fun compareTo(other: Food): Int {
            return if (rating != other.rating) {
                other.rating - rating
            } else {
                name.compareTo(other.name)
            }
        }
    }

    init {
        for (i in foods.indices) {
            val food = foods[i]
            val cuisine = cuisines[i]
            val rating = ratings[i]

            if (cuisine !in cuisineToMaxHeap) {
                cuisineToMaxHeap[cuisine] = PriorityQueue()
            }

            cuisineToMaxHeap[cuisine]!!.offer(Food(food, rating))
            foodToRating[food] = rating
            foodToCuisine[food] = cuisine
        }
    }

    fun changeRating(food: String, newRating: Int) {
        val cuisine = foodToCuisine[food]!!
        foodToRating[food] = newRating

        cuisineToMaxHeap[cuisine]!!.offer(Food(food, newRating))
    }

    fun highestRated(cuisine: String): String {
        val heap = cuisineToMaxHeap[cuisine]!!

        while (heap.isNotEmpty()) {
            val top = heap.peek()
            // 최신 평점과 일치하면 반환
            if (foodToRating[top.name] == top.rating) {
                return top.name
            } else {
                heap.poll()  // 오래된 항목이라 제거
            }
        }

        return ""
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * var obj = FoodRatings(foods, cuisines, ratings)
 * obj.changeRating(food,newRating)
 * var param_2 = obj.highestRated(cuisine)
 */
