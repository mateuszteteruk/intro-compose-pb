package pl.nowoczesnyandroid.introtocompose.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SongsViewModel : ViewModel() {

    private val _songs: MutableStateFlow<List<Song>> = MutableStateFlow(SONGS)
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    fun onFavouriteClick(song: Song) {
        _songs.update { oldList ->
            oldList.map {
                if (it == song) {
                    song.copy(isFavourite = !song.isFavourite)
                } else {
                    it
                }
            }
        }
    }
}

private val SONGS =
    listOf(
        Song(trackName = "Esther", artist = "Popcorn Trees", isFavourite = false),
        Song(trackName = "Golden Hour", artist = "hypewave", isFavourite = true),
        Song(trackName = "Moon", artist = "midnight lover", isFavourite = false),
        Song(trackName = "puppet master", artist = "yamarcus", isFavourite = false),
        Song(trackName = "reflection", artist = "aaesth", isFavourite = false),
        Song(trackName = "shoe polish", artist = "komoere", isFavourite = false),
        Song(trackName = "manuela", artist = "mr moobyy", isFavourite = false),
        Song(trackName = "charm me", artist = "take 1", isFavourite = false),
        Song(trackName = "moving day", artist = "redemoo", isFavourite = false),
        Song(trackName = "main court", artist = "nine ", isFavourite = false),
        Song(trackName = "forgotten notes", artist = "nazaha", isFavourite = false),
        Song(trackName = "night tide", artist = "beaetco", isFavourite = false),
    )

