package za.co.markxh.backpacklifesim

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import za.co.markxh.backpacklifesim.domain.model.*
import java.time.LocalDate
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.json

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    routing {
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
        Item(name = "Old Compass", description = "Still points somewhere.", type = ItemType.TOOL, rarity = Rarity.UNCOMMON, effect = "Changes your career path to explorer."),
        Item(name = "Cup of Coffee", description = "Smells energizing.", type = ItemType.CONSUMABLE, rarity = Rarity.COMMON, effect = "Gives short-term productivity boost."),
        Item(name = "Broken Umbrella", description = "It's seen better days.", type = ItemType.JUNK, rarity = Rarity.COMMON, effect = "Avoids sickness if tossed.")
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
            description = "$effectDescription (Item ID: ${choice.itemId})"
        )
        )
    }
}

fun getLifePath(): LifePath {
    return LifePath(events = lifeEvents)
}