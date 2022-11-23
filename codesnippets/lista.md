# Jak zrobić listę elementów

```kotlin
LazyColumn {
    items(songs, key = { it.id }) { song ->
        SongItem(
            song = song,
            onSongClick = onSongClick,
            onFavouriteClick = onFavouriteClick,
        )
    }
}
```

obserwowanie danych z vm, StateFlow -> State

```kotlin
val songs by songsViewModel.songs.collectAsStateWithLifecycle()
```

element listy:

```kotlin
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
```
