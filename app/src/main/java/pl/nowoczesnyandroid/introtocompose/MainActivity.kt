package pl.nowoczesnyandroid.introtocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pl.nowoczesnyandroid.introtocompose.ui.theme.IntroToComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroToComposeTheme {
                SongsScreen()
            }
        }
    }
}
