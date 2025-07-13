package kotlin

import java.util.*

// https://leetcode.com/problems/maximum-matching-of-players-with-trainers/?envType=daily-question&envId=2025-07-13
class Solution {
    fun matchPlayersAndTrainers(players: IntArray, trainers: IntArray): Int {
        players.sort()
        trainers.sort()

        var result = 0
        var pCursor = 0
        var tCursor = 0

        while (pCursor < players.size && tCursor < trainers.size) {
            if (players[pCursor] <= trainers[tCursor]) {
                result++
                pCursor++
                tCursor++
            } else {
                tCursor++
            }
        }



        return result
    }
}
