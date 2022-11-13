# Animacja favourite

Bez

```kotlin
IconButton(
    modifier = Modifier,
    onClick = { onFavouriteClick(song) },
    content = {
        val icon = if (song.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
        Icon(icon, contentDescription = "")
    },
)
```

Z

```kotlin
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
```
