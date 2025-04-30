package za.co.markxh.backpacklifesim.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import za.co.markxh.backpacklifesim.R

@Composable
actual fun loadMapBackground(): Painter {
    return painterResource(R.drawable.map)
}

@Composable
actual fun loadIcon(iconName: String): Painter {
    TODO("Not yet implemented")
}