package za.co.markxh.backpacklifesim.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.Decision
import za.co.markxh.backpacklifesim.domain.model.Item
import za.co.markxh.backpacklifesim.state.BackpackState
import za.co.markxh.backpacklifesim.state.LifePathState
import za.co.markxh.backpacklifesim.ui.theme.AppSpacing
import za.co.markxh.backpacklifesim.ui.theme.AppStrings
import za.co.markxh.backpacklifesim.ui.theme.BackgroundColor
import za.co.markxh.backpacklifesim.ui.theme.CyanAccent
import za.co.markxh.backpacklifesim.ui.theme.GreenAccent
import za.co.markxh.backpacklifesim.ui.theme.PrimaryColor
import za.co.markxh.backpacklifesim.ui.theme.RedAccent
import za.co.markxh.backpacklifesim.ui.theme.SecondaryTextColor

@Composable
fun BackpackScreen(
    backpackState: BackpackState,
    lifePathState: LifePathState,
    onItemChoice: (itemId: String, name: String, decision: Decision) -> Unit,
    onSubmitChoices: () -> Unit,
    onLoadBackpack: () -> Unit,
    onNavigateToLifePath: () -> Unit,
    modifier: Modifier = Modifier,
    selectedChoices: List<Choice>
) {
    LaunchedEffect(Unit) { onLoadBackpack() }

    LaunchedEffect(selectedChoices.size, backpackState) {
        if (backpackState is BackpackState.Loaded &&
            selectedChoices.size == backpackState.backpack.items.size
        ) {
            onSubmitChoices()
        }
    }

    LaunchedEffect(lifePathState) {
        if (lifePathState is LifePathState.Loaded) {
            onNavigateToLifePath()
        }
    }

    when (backpackState) {
        is BackpackState.Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
        is BackpackState.Error -> ErrorText(backpackState.message)
        is BackpackState.Loaded -> BackpackItemList(
            items = backpackState.backpack.items,
            selectedChoices = selectedChoices,
            onItemChoice = onItemChoice,
            modifier = modifier
        )
        is BackpackState.Submitted -> LoadingLifePathScreen()
    }
}

@Composable
fun BackpackItemList(
    items: List<Item>,
    selectedChoices: List<Choice>,
    onItemChoice: (itemId: String, name: String, decision: Decision) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(AppSpacing.medium),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = AppStrings.todaysBackpack,
                style = MaterialTheme.typography.h5,
                color = PrimaryColor,
                modifier = Modifier
                    .padding(AppSpacing.medium)
            )
        }

        items(items) { item ->
            if (selectedChoices.none { it.itemId == item.id }) {
                ItemCard(item = item, onItemChoice = onItemChoice)
            }
        }
    }
}

@Composable
fun ItemCard(
    item: Item,
    onItemChoice: (itemId: String, name: String, decision: Decision) -> Unit
) {
    val scope = rememberCoroutineScope()
    var visible by remember { mutableStateOf(true) }

    val animatedOffsetX by animateDpAsState(
        targetValue = if (visible) 0.dp else 500.dp,
        animationSpec = tween(durationMillis = 300),
        label = "DismissAnimation"
    )

    AnimatedVisibility(
        visible = visible,
        exit = shrinkHorizontally(animationSpec = tween(300)) + fadeOut(tween(300))
    ) {
        Card(
            elevation = AppSpacing.small,
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = animatedOffsetX)
        ) {
            Row(
                modifier = Modifier
                    .padding(AppSpacing.medium)
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.name,
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.width(AppSpacing.medium))

                Column(
                    verticalArrangement = Arrangement.spacedBy(AppSpacing.small),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(item.name, style = MaterialTheme.typography.h6, color = PrimaryColor)
                    Text(item.description, style = MaterialTheme.typography.subtitle2, color = SecondaryTextColor)
                    Text(
                        "Type: ${item.type.name.lowercase().replaceFirstChar { it.uppercase() }}",
                        style = MaterialTheme.typography.overline,
                        color = SecondaryTextColor
                    )
                    Text(
                        "Rarity: ${item.rarity.name.lowercase().replaceFirstChar { it.uppercase() }}",
                        style = MaterialTheme.typography.overline,
                        color = SecondaryTextColor
                    )
                    Text(
                        "Effect: ${item.effect}",
                        style = MaterialTheme.typography.overline,
                        color = SecondaryTextColor
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Decision.entries.forEach { decision ->
                            Text(
                                decision.displayName(),
                                style = MaterialTheme.typography.subtitle2,
                                color = when (decision) {
                                    Decision.KEEP -> GreenAccent
                                    Decision.USE -> CyanAccent
                                    Decision.TOSS -> RedAccent
                                },
                                modifier = Modifier.clickable {
                                    visible = false
                                    scope.launch {
                                        delay(300)
                                        onItemChoice(item.id, item.name, decision)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingLifePathScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoadingIndicator()
        Spacer(modifier = Modifier.height(AppSpacing.medium))
        Text(
            AppStrings.shapingLifePath,
            style = MaterialTheme.typography.h6,
            color = PrimaryColor
        )
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PrimaryColor)
    }
}

@Composable
fun ErrorText(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Color.Red,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
    }
}