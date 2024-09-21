package de.xyndra.knowledgeapi.logic.calendar

import de.xyndra.knowledgeapi.util.DateTime
import de.xyndra.knowledgeapi.util.maxOf
import kotlinx.serialization.Serializable
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days

typealias Timespan = Pair<DateTime, DateTime>

/**
 * This handles repeating time slots. Be warned: Complexity because of time zones, leap seconds, summer vs winter, etc.
 */
@Serializable
sealed class Repeats(val repeatsForTimespan: (DateTime, Timespan) -> List<DateTime>?, val lengthForTime: (DateTime) -> Duration) {
    data class None(val duration: Duration) : Repeats ({ _, _ -> null }, { duration })
    data class Daily(val duration: Duration) : Repeats ({ original, (from, until) ->
        val result = mutableListOf<DateTime>()
        var current = maxOf(original, from)
        while (current < until) {
            result.add(original.getWithDate(current))
            current = current.plus(1.days)
        }
        result
    }, { duration })
}