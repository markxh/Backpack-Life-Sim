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
import androidx.compose.ui.unit.dp
import za.co.markxh.backpacklifesim.state.LifePathState
import za.co.markxh.backpacklifesim.ui.ErrorText
import za.co.markxh.backpacklifesim.ui.LoadingIndicator
import za.co.markxh.backpacklifesim.ui.theme.AppSpacing
import za.co.markxh.backpacklifesim.ui.theme.AppStrings
import za.co.markxh.backpacklifesim.ui.theme.BackgroundColor
import za.co.markxh.backpacklifesim.ui.theme.PrimaryColor
import za.co.markxh.backpacklifesim.ui.theme.SecondaryTextColor

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
            text = AppStrings.yourLifePath,
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
                    verticalArrangement = Arrangement.spacedBy(AppSpacing.small),
                    modifier = Modifier.weight(1f)
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
                                    color = SecondaryTextColor
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = event.description,
                                    style = MaterialTheme.typography.body1,
                                    color = PrimaryColor
                                )
                            }
                        }
                    }
                }
            }

            is LifePathState.Idle -> {
                Text(
                    text = "Waiting for your life path...",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.body2,
                    color = SecondaryTextColor
                )
            }
        }

        Spacer(modifier = Modifier.height(AppSpacing.medium))

        Button(onClick = onBack, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(AppStrings.back)
        }
    }
}
