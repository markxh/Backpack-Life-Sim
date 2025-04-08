package za.co.markxh.backpacklifesim.utils

import platform.Foundation.NSUUID

actual fun generateUUID() = NSUUID.UUID().UUIDString