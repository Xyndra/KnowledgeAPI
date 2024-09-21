package de.xyndra.knowledgeapi.logic

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val uid: UID,
    val preferences: MutableMap<String, String> = remember(uid.toString(), "preferences") { mutableMapOf() },
    val calendar: Calendar = remember(uid.toString(), "calendar") { Calendar() },

)

val data = mutableMapOf(
    0 to UserData(0),
)