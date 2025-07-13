package kotlin

import java.util.*

// https://leetcode.com/problems/maximum-matching-of-players-with-trainers/?envType=daily-question&envId=2025-07-13
class Solution {
    fun matchPlayersAndTrainers(players: IntArray, trainers: IntArray): Int {
        val playerQueue = PriorityQueue<Int>()
        for (player in players) {
            playerQueue.add(player)
        }

        val trainerQueue = PriorityQueue<Int>()
        for (trainer in trainers) {
            trainerQueue.add(trainer)
        }

        var result = 0

        while (playerQueue.isNotEmpty() && trainerQueue.isNotEmpty()) {
            val player = playerQueue.peek()
            val trainer = trainerQueue.poll()
            if (player <= trainer) {
                playerQueue.poll()
                result++
            }
        }

        return result
    }
}
