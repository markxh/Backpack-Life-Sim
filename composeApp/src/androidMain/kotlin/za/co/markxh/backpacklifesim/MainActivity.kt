package za.co.markxh.backpacklifesim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.viewmodel.ext.android.viewModel
import za.co.markxh.backpacklifesim.ui.AndroidBackpackScreen
import za.co.markxh.backpacklifesim.ui.BackpackViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: BackpackViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AndroidBackpackScreen()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}