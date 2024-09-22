package de.xyndra.knowledgeapi.util

import kotlinx.serialization.Serializable

@Serializable
data class Color(
    val red: Int,
    val green: Int,
    val blue: Int,
    val alpha: Int = 255,
) {
    init {
        require(red in 0..255) { "Red must be between 0 and 255" }
        require(green in 0..255) { "Green must be between 0 and 255" }
        require(blue in 0..255) { "Blue must be between 0 and 255" }
        require(alpha in 0..255) { "Alpha must be between 0 and 255" }
    }

    companion object {
        val White = Color(255, 255, 255)
        val Black = Color(0, 0, 0)
        val Red = Color(255, 0, 0)
        val Green = Color(0, 255, 0)
        val Blue = Color(0, 0, 255)
    }
}