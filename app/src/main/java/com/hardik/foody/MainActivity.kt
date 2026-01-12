package com.hardik.foody

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hardik.foody.ui.theme.FoodyTheme

class MainActivity : ComponentActivity() {
    /**
     * Initializes the activity, enables edge-to-edge display, and sets the Compose UI content.
     *
     * The content applies FoodyTheme and places a full-screen Scaffold that renders the Greeting
     * composable.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

/**
 * Displays a greeting text "Hello <name>!".
 *
 * @param name The name to show in the greeting.
 * @param modifier Modifier to apply to the Text composable. */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

/**
 * Shows a preview of the Greeting composable wrapped in the app theme.
 *
 * This function renders the Greeting composable with the name "Android" for IDE previews.
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodyTheme {
        Greeting("Android")
    }
}