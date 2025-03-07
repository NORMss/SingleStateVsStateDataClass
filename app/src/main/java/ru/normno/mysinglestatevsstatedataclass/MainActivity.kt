package ru.normno.mysinglestatevsstatedataclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.normno.mysinglestatevsstatedataclass.ui.theme.MySingleStateVsStateDataClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySingleStateVsStateDataClassTheme {

                val viewModelA by viewModels<ViewModelA>()
                RegisterScreenA(
                    titleScreen = "Screen A",
                    login = viewModelA.email.collectAsState().value,
                    password = viewModelA.password.collectAsState().value,
                    isLoginValid = viewModelA.isEmailValid.collectAsState().value,
                    isPasswordValid = viewModelA.isPasswordValid.collectAsState().value,
                    canRegister = viewModelA.canRegister.collectAsState().value,
                    setLogin = viewModelA::updateEmail,
                    setPassword = viewModelA::updatePassword,
                )

//                val viewModelB by viewModels<ViewModelB>()
//                val state by viewModelB.state.collectAsState()
//                RegisterScreenB(
//                    titleScreen = "Screen B",
//                    login = state.email,
//                    password = state.password,
//                    isLoginValid = state.isEmailValid,
//                    isPasswordValid = state.isPasswordValid,
//                    canRegister = state.canRegister,
//                    setLogin = viewModelB::updateEmail,
//                    setPassword = viewModelB::updatePassword,
//                )
            }
        }
    }
}

@Composable
fun RegisterScreenA(
    titleScreen: String? = null,
    login: String,
    password: String,
    isLoginValid: Boolean,
    isPasswordValid: Boolean,
    canRegister: Boolean,
    setLogin: (String) -> Unit,
    setPassword: (String) -> Unit,
) {
    InputCredential(
        titleScreen = titleScreen,
        login = login,
        password = password,
        isLoginValid = isLoginValid,
        isPasswordValid = isPasswordValid,
        canRegister = canRegister,
        setLogin = setLogin,
        setPassword = setPassword
    )
}

@Composable
fun RegisterScreenB(
    titleScreen: String? = null,
    login: String,
    password: String,
    isLoginValid: Boolean,
    isPasswordValid: Boolean,
    canRegister: Boolean,
    setLogin: (String) -> Unit,
    setPassword: (String) -> Unit,
) {
    InputCredential(
        titleScreen = titleScreen,
        login = login,
        password = password,
        isLoginValid = isLoginValid,
        isPasswordValid = isPasswordValid,
        canRegister = canRegister,
        setLogin = setLogin,
        setPassword = setPassword
    )
}

@Composable
fun InputCredential(
    titleScreen: String? = null,
    login: String,
    password: String,
    isLoginValid: Boolean,
    isPasswordValid: Boolean,
    canRegister: Boolean,
    setLogin: (String) -> Unit,
    setPassword: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        titleScreen?.let {
            Text(
                text = titleScreen,
                style = MaterialTheme.typography.labelLarge,
            )
        }
        Spacer(
            modifier = Modifier
                .height(32.dp)
        )
        TextField(
            value = login,
            onValueChange = setLogin,
            placeholder = {
                Text(
                    text = "Login"
                )
            },
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        TextField(
            value = password,
            onValueChange = setPassword,
            placeholder = {
                Text(
                    text = "Password"
                )
            },
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        Text(
            text = when {
                !isLoginValid -> "Login is not valid"

                !isPasswordValid -> "Password is not valid"

                !canRegister -> "You can be registered!"

                else -> ""
            },
            color = when {
                !isLoginValid -> MaterialTheme.colorScheme.error

                !isPasswordValid -> MaterialTheme.colorScheme.error

                !canRegister -> MaterialTheme.colorScheme.primary

                else -> Color.Unspecified
            },
            style = MaterialTheme.typography.titleLarge,
        )
    }
}