package kotlin

import java.util.*
import kotlin.math.ceil

// https://leetcode.com/problems/implement-router/?envType=daily-question&envId=2025-09-20
class Router(memoryLimit: Int) {

    private val packetSet = mutableSetOf<Packet>()
    private val packetQueue = LinkedList<Packet>()
    private val limit = memoryLimit

    private val destinationLists = mutableMapOf<Int, MutableList<Packet>>()

    fun addPacket(source: Int, destination: Int, timestamp: Int): Boolean {
        val next = Packet(source, destination, timestamp)

        if (packetSet.contains(next)) {
            return false
        }

        if (packetQueue.size >= limit) {
            val oldest = packetQueue.poll()
            removeFromDestinationList(oldest)
            packetSet.remove(oldest)
        }

        packetQueue.add(next)
        packetSet.add(next)
        addToDestinationList(next)
        return true
    }

    fun forwardPacket(): IntArray {
        val packet = packetQueue.poll()
        if (packet == null) {
            return intArrayOf()
        }

        packetSet.remove(packet)
        removeFromDestinationList(packet)
        return intArrayOf(packet.source, packet.destination, packet.timestamp)
    }


    fun getCount(destination: Int, startTime: Int, endTime: Int): Int {
        val packetList = destinationLists[destination] ?: return 0

        val startIndex = findFirstGreaterOrEqual(packetList, startTime)
        if (startIndex >= packetList.size) return 0

        val endIndex = findLastLessOrEqual(packetList, endTime)
        if (endIndex < 0) return 0

        return if (endIndex >= startIndex) endIndex - startIndex + 1 else 0
    }

    private fun addToDestinationList(packet: Packet) {
        val list = destinationLists.computeIfAbsent(packet.destination) { mutableListOf() }
        list.add(packet)
    }

    private fun removeFromDestinationList(packet: Packet) {
        val list = destinationLists[packet.destination] ?: return
        list.remove(packet)

        if (list.isEmpty()) {
            destinationLists.remove(packet.destination)
        }
    }

    private fun findFirstGreaterOrEqual(list: List<Packet>, target: Int): Int {
        var left = 0
        var right = list.size

        while (left < right) {
            val mid = (left + right) / 2
            if (list[mid].timestamp >= target) {
                right = mid
            } else {
                left = mid + 1
            }
        }

        return left
    }

    private fun findLastLessOrEqual(list: List<Packet>, target: Int): Int {
        var left = -1
        var right = list.size - 1

        while (left < right) {
            val mid = (left + right + 1) / 2
            if (list[mid].timestamp <= target) {
                left = mid
            } else {
                right = mid - 1
            }
        }

        return left
    }


    private data class Packet(
        val source: Int,
        val destination: Int,
        val timestamp: Int
    )
}
