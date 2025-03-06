package ru.normno.mysinglestatevsstatedataclass

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
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
import ru.normno.mysinglestatevsstatedataclass.ui.theme.MySingleStateVsStateDataClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySingleStateVsStateDataClassTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}

@Composable
fun RegisterScreenA(
    email: String,
    password: String,
) {

}

@Composable
fun RegisterScreenB(
    state: MainState
) {

}