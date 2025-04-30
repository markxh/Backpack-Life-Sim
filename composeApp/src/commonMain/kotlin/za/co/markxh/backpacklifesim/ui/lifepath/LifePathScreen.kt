package za.co.markxh.backpacklifesim.ui.lifepath

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import za.co.markxh.backpacklifesim.state.LifePathState
import za.co.markxh.backpacklifesim.ui.ErrorText
import za.co.markxh.backpacklifesim.ui.LoadingIndicator
import za.co.markxh.backpacklifesim.ui.theme.AppSpacing
import za.co.markxh.backpacklifesim.ui.theme.BackgroundColor
import za.co.markxh.backpacklifesim.ui.theme.PrimaryColor

@Composable
fun LifePathScreen(
    state: LifePathState,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(AppSpacing.medium)
    ) {
        Text(
            text = "Your Life Path",
            style = MaterialTheme.typography.h5,
            color = PrimaryColor,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(AppSpacing.medium))

        when (state) {
            is LifePathState.Loading -> {
                LoadingIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is LifePathState.Error -> {
                ErrorText(state.message)
            }
            is LifePathState.Loaded -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(AppSpacing.small)
                ) {
                    items(state.lifePath.events) { event ->
                        Card(
                            backgroundColor = Color.White,
                            elevation = AppSpacing.small,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(AppSpacing.medium)) {
                                Text(
                                    text = event.date,
                                    style = MaterialTheme.typography.caption,
                                    color = Color.Gray
                                )
                                Text(
                                    text = event.description,
                                    style = MaterialTheme.typography.body1,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }

            LifePathState.Idle -> TODO()
        }

        Spacer(modifier = Modifier.height(AppSpacing.medium))

        Button(onClick = onBack, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Back")
        }
    }
}
