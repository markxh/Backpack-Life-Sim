package za.co.markxh.backpacklifesim.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun AndroidBackpackScreen(
    viewModel: BackpackViewModel = koinViewModel(),
    onNavigateToLifePath: () -> Unit
) {
    val backpackState by viewModel.backpackState.collectAsState()
    val lifePathState by viewModel.lifePathState.collectAsState()
    val selectedChoices = viewModel.selectedChoices

    BackpackScreen(
        backpackState = backpackState,
        lifePathState = lifePathState,
        selectedChoices = selectedChoices,
        onItemChoice = viewModel::updateChoice,
        onNavigateToLifePath = {
            onNavigateToLifePath()
        },
        modifier = Modifier.fillMaxSize()
    )
}