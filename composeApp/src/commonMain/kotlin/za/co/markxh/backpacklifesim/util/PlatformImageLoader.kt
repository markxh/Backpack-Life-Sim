package za.co.markxh.backpacklifesim.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
expect fun loadMapBackground(): Painter

@Composable
expect fun loadIcon(iconName: String): Painter