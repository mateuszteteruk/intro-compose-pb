```kotlin
Scaffold(
    modifier = Modifier.fillMaxSize(),
    scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
    topBar = { TopAppBar(title = { Text(text = "Songs") }) },
) {
    // content ...
}
```

