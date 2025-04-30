package za.co.markxh.backpacklifesim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import za.co.markxh.backpacklifesim.navigation.BackpackNavGraph
import za.co.markxh.backpacklifesim.ui.BackpackViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: BackpackViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                BackpackNavGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}