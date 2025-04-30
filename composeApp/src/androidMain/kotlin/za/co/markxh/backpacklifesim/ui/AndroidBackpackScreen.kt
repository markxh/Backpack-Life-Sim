package za.co.markxh.backpacklifesim.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import za.co.markxh.backpacklifesim.state.BackpackState

@Composable
fun AndroidBackpackScreen(
    viewModel: BackpackViewModel = koinViewModel(),
    onNavigateToLifePath: () -> Unit
) {
    val backpackState by viewModel.backpackState.collectAsState()
    val selectedChoices = viewModel.selectedChoices

    LaunchedEffect(backpackState) {
        if (backpackState is BackpackState.Submitted) {
            val remaining = (3000 - viewModel.timeSinceSubmission()).coerceAtLeast(0)
            delay(remaining)
            onNavigateToLifePath()
        }
    }

    BackpackScreen(
        backpackState = backpackState,
        selectedChoices = selectedChoices,
        onItemChoice = viewModel::updateChoice,
        onLoadBackpack = viewModel::loadBackpack,
        onSubmitChoices = viewModel::finalizeSubmission,
        onNavigateToLifePath = onNavigateToLifePath,
        modifier = Modifier.fillMaxSize()
    )
}