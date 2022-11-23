package pl.nowoczesnyandroid.introtocompose.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import pl.nowoczesnyandroid.introtocompose.R

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun SongsScreen(
    songsViewModel: SongsViewModel = viewModel(),
) {
    val songs by songsViewModel.songs.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    SongsScreen(
        snackbarHostState = snackbarHostState,
        songs = songs,
        onSongClick = {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("You selected: ${it.trackName} by ${it.artist}")
            }
        },
        onFavouriteClick = songsViewModel::onFavouriteClick,
    )
}

@Composable
private fun SongsScreen(
    songs: List<Song>,
    onSongClick: (Song) -> Unit,
    onFavouriteClick: (Song) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        topBar = { TopAppBar(title = { Text(text = "Songs") }) },
    ) {
        LazyColumn {
            items(songs, key = { it.id }) { song ->
                SongItem(
                    song = song,
                    onSongClick = onSongClick,
                    onFavouriteClick = onFavouriteClick,
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SongItem(
    song: Song,
    onSongClick: (Song) -> Unit,
    onFavouriteClick: (Song) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSongClick(song) }
            .padding(all = 10.dp)
    ) {
        Image(
            modifier = Modifier
                .align(CenterVertically)
                .padding(horizontal = 10.dp),
            painter = painterResource(id = R.drawable.ic_android),
            contentDescription = "cover",
        )
        Column(
            modifier = Modifier.weight(1F),
        ) {
            Text(
                text = song.trackName,
                fontSize = 15.sp,
            )
            Text(
                text = song.artist,
                fontSize = 12.sp,
            )
        }
        AnimatedContent(targetState = song.isFavourite) {
            IconButton(
                modifier = Modifier,
                onClick = { onFavouriteClick(song) },
                content = {
                    val icon = if (it) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
                    Icon(icon, contentDescription = "")
                },
            )
        }
    }
}
