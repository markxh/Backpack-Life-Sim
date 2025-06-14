import za.co.markxh.backpacklifesim.util.Clock

class TestClock : Clock {
    var currentTime: Long = 0L
    override fun currentTimeMillis(): Long = currentTime
}