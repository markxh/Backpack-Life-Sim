package za.co.markxh.backpacklifesim.util

import io.ktor.util.date.getTimeMillis

class AndroidClock : Clock {
    override fun currentTimeMillis(): Long = getTimeMillis()
}