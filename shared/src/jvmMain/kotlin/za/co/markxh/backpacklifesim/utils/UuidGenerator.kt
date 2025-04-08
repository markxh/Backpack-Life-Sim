package za.co.markxh.backpacklifesim.utils

import java.util.UUID

actual fun generateUUID(): String = UUID.randomUUID().toString()