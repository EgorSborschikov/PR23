package com.example.pr23_dmitrygolobokov_pr23106

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class LogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogInScreen()
        }
    }
}

@Composable
fun LogInScreen() {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LogInScreen()
}