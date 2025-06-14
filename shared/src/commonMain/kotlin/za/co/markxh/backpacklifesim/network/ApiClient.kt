package za.co.markxh.backpacklifesim.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.LifePath

class ApiClient {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    private val baseUrl = "http://10.0.2.2:8080"

    suspend fun fetchBackpack(): Backpack {
        return httpClient.get("$baseUrl/backpack/today").body()
    }

    suspend fun submitChoices(choices: List<Choice>): Result<Unit> {
        return runCatching {
            httpClient.post("$baseUrl/backpack/choose") {
                contentType(ContentType.Application.Json)
                setBody(choices)
            }
        }
    }

    suspend fun fetchLifePath(): LifePath {
        return httpClient.get("$baseUrl/life-path").body()
    }
}