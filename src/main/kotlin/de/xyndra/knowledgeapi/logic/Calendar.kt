package de.xyndra.knowledgeapi.logic

import de.xyndra.knowledgeapi.logic.calendar.Repeats
import de.xyndra.knowledgeapi.util.Color
import de.xyndra.knowledgeapi.util.DateTime
import kotlinx.serialization.Serializable

@Serializable
data class TimeSlot(
    val start: DateTime,
    val end: DateTime,
    val name: String,
    val description: String = "",
    val color: Color = Color.Blue,
    val tags: List<String> = emptyList(),
    val repeats: Repeats = Repeats.None(end - start),
)

@Serializable
data class Calendar (
    val timeSlots: MutableMap<String, TimeSlot> = mutableMapOf(),
)