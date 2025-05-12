package za.co.markxh.backpacklifesim

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticResources
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.Decision
import za.co.markxh.backpacklifesim.domain.model.Item
import za.co.markxh.backpacklifesim.domain.model.ItemType
import za.co.markxh.backpacklifesim.domain.model.LifeEvent
import za.co.markxh.backpacklifesim.domain.model.LifePath
import za.co.markxh.backpacklifesim.domain.model.Rarity
import java.time.LocalDate

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        staticResources("/static", "static")

        get("/backpack/today") {
            call.respond(generateBackpack())
        }

        post("/backpack/choose") {
            val choices = call.receive<List<Choice>>()
            applyChoices(choices)
            call.respond(HttpStatusCode.OK)
        }

        get("/life-path") {
            call.respond(getLifePath())
        }
    }
}

fun generateBackpack(): Backpack {
    val items = listOf(
        Item(
            name = "Old Compass",
            description = "Still points somewhere.",
            type = ItemType.TOOL,
            rarity = Rarity.UNCOMMON,
            effect = "Changes your career path to explorer.",
            imageUrl = "http://10.0.2.2:8080/static/images/compass.webp"
        ),
        Item(
            name = "Cup of Coffee",
            description = "Smells energizing.",
            type = ItemType.CONSUMABLE,
            rarity = Rarity.COMMON,
            effect = "Gives short-term productivity boost.",
            imageUrl = "http://10.0.2.2:8080/static/images/coffee.webp"
        ),
        Item(
            name = "Broken Umbrella",
            description = "It's seen better days.",
            type = ItemType.JUNK,
            rarity = Rarity.COMMON,
            effect = "Avoids sickness if tossed.",
            imageUrl = "http://10.0.2.2:8080/static/images/ugly_umbrella.webp"
        )
    ).shuffled()

    return Backpack(
        date = LocalDate.now().toString(),
        items = items
    )
}

val lifeEvents = mutableListOf<LifeEvent>()

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

fun getLifePath(): LifePath {
    return LifePath(events = lifeEvents)
}