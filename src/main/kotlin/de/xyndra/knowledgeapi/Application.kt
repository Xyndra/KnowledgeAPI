package de.xyndra.knowledgeapi

import de.xyndra.knowledgeapi.plugins.configureHTTP
import de.xyndra.knowledgeapi.plugins.configureRouting
import de.xyndra.knowledgeapi.plugins.configureSerialization
import de.xyndra.knowledgeapi.plugins.configureSockets
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSockets()
    configureSerialization()
    configureHTTP()
    configureRouting()
}
