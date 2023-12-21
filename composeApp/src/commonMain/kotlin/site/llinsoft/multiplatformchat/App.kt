package site.llinsoft.multiplatformchat

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import site.llinsoft.multiplatformchat.screens.main_screen.MainScreen
import site.llinsoft.multiplatformchat.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun App() = AppTheme {

    Navigator(MainScreen()) { navigator ->
        SlideTransition(
            navigator = navigator,
        )
    }



/*    Navigator(screen = ScreenA()) { navigator ->
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text("Voyager navigation") }
                )
            }
        ) { innerPadding ->
            SlideTransition(
                navigator = navigator,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }*/

/*    TabNavigator(tab = TabA) {
        Scaffold (
            topBar = {
                NavigationBar {
                    TabItem(TabA)
                    TabItem(TabB)
                }
            }
        ) {
            CurrentTab()
        }
    }*/


    /*var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {

        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.weight(1.0f))

            var isDark by LocalThemeIsDark.current
            IconButton(
                onClick = { isDark = !isDark }
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp).size(20.dp),
                    imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDescription = null
                )
            }
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    val imageVector = if (passwordVisibility) Icons.Default.Close else Icons.Default.Edit
                    Icon(imageVector, contentDescription = if (passwordVisibility) "Hide password" else "Show password")
                }
            }
        )

        Button(
            onClick = { *//* Handle login logic here *//* },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text("Login")
        }

        TextButton(
            onClick = { openUrl("https://github.com/terrakok") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text("Open github")
        }
    }*/
}

@Composable
private fun RowScope.TabItem(tab: Tab) {

    val tabNavigator = LocalTabNavigator.current

    NavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let { painter ->
                Icon(painter, contentDescription = tab.options.title)
            }
        }
    )
}

internal expect fun openUrl(url: String?)