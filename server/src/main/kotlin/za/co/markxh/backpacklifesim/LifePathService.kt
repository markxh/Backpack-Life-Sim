package za.co.markxh.backpacklifesim

import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.Decision
import za.co.markxh.backpacklifesim.domain.model.LifeEvent
import za.co.markxh.backpacklifesim.domain.model.LifePath
import java.time.LocalDate

object LifePathService {
    private val lifeEvents = mutableListOf<LifeEvent>()

    fun applyChoices(choices: List<Choice>) {
        for (choice in choices) {
            val effectDescription = when (choice.decision) {
                Decision.KEEP -> "You chose to hold onto the item."
                Decision.USE -> "You used the item and something changed."
                Decision.TOSS -> "You let go of the item."
            }

            lifeEvents.add(
                LifeEvent(
                    date = LocalDate.now().toString(),
                    description = effectDescription,
                    iconName = "placeholder_icon",
                    title = choice.name
                )
            )
        }
    }

    fun getLifePath(): LifePath = LifePath(events = lifeEvents)

    fun clearEvents() {
        lifeEvents.clear()
    }
}