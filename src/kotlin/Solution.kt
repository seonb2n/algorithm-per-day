package kotlin

import java.util.*
import kotlin.collections.HashSet
import kotlin.math.floor
import kotlin.math.sqrt


// https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/?envType=daily-question&envId=2025-03-21
class Solution {
    fun findAllRecipes(recipes: Array<String>, ingredients: List<List<String>>, supplies: Array<String>): List<String> {
        // 공급 가능한 재료들을 집합으로 변환 (O(1) 검색을 위해)
        val availableSupplies = supplies.toSet()

        // 레시피 이름을 키로, 재료 리스트를 값으로 하는 맵 생성
        val recipeMap = mutableMapOf<String, List<String>>()
        for (i in recipes.indices) {
            recipeMap[recipes[i]] = ingredients[i]
        }

        // 메모이제이션을 위한 맵 (방문 상태 + 결과 캐싱)
        // 0: 미방문, 1: 방문 중 (사이클 감지용), 2: 만들 수 있음, 3: 만들 수 없음
        val memo = mutableMapOf<String, Int>()

        val result = mutableListOf<String>()

        // 각 레시피에 대해 DFS 수행
        for (recipe in recipes) {
            if (dfs(recipe, recipeMap, availableSupplies, memo)) {
                result.add(recipe)
            }
        }

        return result
    }

    private fun dfs(
        item: String,
        recipeMap: Map<String, List<String>>,
        supplies: Set<String>,
        memo: MutableMap<String, Int>
    ): Boolean {
        // 메모이제이션: 이미 결과를 알고 있는 경우
        when (memo[item]) {
            2 -> return true     // 만들 수 있음
            3 -> return false    // 만들 수 없음
            1 -> return false    // 사이클 발견 (현재 경로에서 이미 방문함)
        }

        // 기본 재료인 경우 (supplies에 있으면)
        if (item in supplies) {
            memo[item] = 2   // 만들 수 있음
            return true
        }

        // 레시피가 없는 경우
        if (item !in recipeMap) {
            memo[item] = 3   // 만들 수 없음
            return false
        }

        // 방문 중으로 표시 (사이클 감지용)
        memo[item] = 1

        // 모든 재료에 대해 DFS 수행
        for (ingredient in recipeMap[item]!!) {
            if (!dfs(ingredient, recipeMap, supplies, memo)) {
                memo[item] = 3   // 만들 수 없음
                return false
            }
        }

        // 모든 재료를 만들 수 있으므로 이 레시피도 만들 수 있음
        memo[item] = 2
        return true
    }
}
