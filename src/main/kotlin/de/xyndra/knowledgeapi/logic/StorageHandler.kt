package de.xyndra.knowledgeapi.logic

import kotlinx.serialization.Serializable

// TODO: Implement this
inline fun <reified T> remember(user: String?, name: String, defaultValue: () -> T): T where T : @Serializable Any {
    return defaultValue()
}