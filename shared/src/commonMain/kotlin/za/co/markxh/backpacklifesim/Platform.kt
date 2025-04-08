package za.co.markxh.backpacklifesim

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform