package de.xyndra.knowledgeapi.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.ZonedDateTime
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Serializable(with = DateTimeSerializer::class)
@JvmInline
value class DateTime(private val zonedDateTime: ZonedDateTime) {
    override fun toString(): String {
        return zonedDateTime.toString()
    }

    fun getWithDate(other: DateTime): DateTime {
        return DateTime(
            ZonedDateTime.of(
                other.zonedDateTime.year,
                other.zonedDateTime.monthValue,
                other.zonedDateTime.dayOfMonth,
                zonedDateTime.hour,
                zonedDateTime.minute,
                zonedDateTime.second,
                zonedDateTime.nano,
                zonedDateTime.zone
            )
        )
    }

    operator fun plus(duration: Duration): DateTime {
        return DateTime(zonedDateTime.plusNanos(duration.inWholeNanoseconds))
    }

    operator fun minus(duration: Duration): DateTime {
        return DateTime(zonedDateTime.minusNanos(duration.inWholeNanoseconds))
    }

    operator fun minus(other: DateTime): Duration {
        return (zonedDateTime.toInstant().toEpochMilli() - other.zonedDateTime.toInstant().toEpochMilli()).milliseconds
    }

    operator fun compareTo(other: DateTime): Int {
        return zonedDateTime.compareTo(other.zonedDateTime)
    }
}

fun maxOf(a: DateTime, b: DateTime): DateTime {
    return if (a > b) a else b
}

class DateTimeSerializer : KSerializer<DateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DateTime) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): DateTime {
        return DateTime(ZonedDateTime.parse(decoder.decodeString()))
    }
}
