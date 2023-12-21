import androidx.compose.ui.window.ComposeUIViewController
import site.llinsoft.multiplatformchat.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
