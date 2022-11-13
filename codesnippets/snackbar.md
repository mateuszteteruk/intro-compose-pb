# Jak zrobić snackbar

```kotlin
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // ...

    onSongClick = {
        coroutineScope.launch {
            snackbarHostState.showSnackbar("You selected: ${it.trackName} by ${it.artist}")
        }
    }
```


