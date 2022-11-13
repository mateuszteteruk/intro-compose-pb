package pl.nowoczesnyandroid.introtocompose.ui

import java.util.UUID

data class Song(
    val trackName: String,
    val artist: String,
    val isFavourite: Boolean,
) {

    val id: String = UUID.randomUUID().toString()
}
