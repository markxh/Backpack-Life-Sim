package za.co.markxh.backpacklifesim.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import za.co.markxh.backpacklifesim.ui.AndroidBackpackScreen
import za.co.markxh.backpacklifesim.ui.BackpackViewModel
import za.co.markxh.backpacklifesim.ui.lifepath.LifePathScreen

object Routes {
    const val Backpack = "backpack"
    const val LifePath = "lifePath"
}

@Composable
fun BackpackNavGraph(
    navController: NavHostController,
    viewModel: BackpackViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Backpack
    ) {
        composable(Routes.Backpack) {
            AndroidBackpackScreen(viewModel) {
                navController.navigate(Routes.LifePath)
            }
        }

        composable(Routes.LifePath) {
            val lifePathState by viewModel.lifePathState.collectAsState()
            LifePathScreen(
                state = lifePathState,
                onBack = { navController.popBackStack() }
            )
//            viewModel.clearLifePathState()
        }
    }
}

