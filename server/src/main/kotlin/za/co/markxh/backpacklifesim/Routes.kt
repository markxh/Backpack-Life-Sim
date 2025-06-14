package za.co.markxh.backpacklifesim

import io.ktor.server.application.Application
import io.ktor.server.http.content.staticResources
import io.ktor.server.request.receive
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.util.reflect.typeInfo
import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.LifePath

fun Application.configureRoutes() {
    routing {
        staticResources("/static", "static")

        get("/backpack/today") {
            call.respond(
                generateBackpack(),
                typeInfo = typeInfo<Backpack>()
            )
        }

        post("/backpack/choose") {
            val choices = call.receive<List<Choice>>()
            LifePathService.applyChoices(choices)
            call.respond(
                mapOf("status" to "choices applied"),
                typeInfo = typeInfo<Map<String, String>>()
            )
        }

        get("/life-path") {
            call.respond(
                LifePathService.getLifePath(),
                typeInfo = typeInfo<LifePath>()
            )
        }
    }
}