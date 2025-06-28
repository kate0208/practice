package com.example.katepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.katepractice.ui.ContentBlue
import com.example.katepractice.ui.ContentGreen
import com.example.katepractice.ui.ContentMauve
import com.example.katepractice.ui.ContentYellow
import com.example.katepractice.ui.theme.AppTheme
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey, BottomNavItem {
    override val icon: ImageVector
        get() = Icons.Filled.Home
    override val title: String
        get() = "Home"
}

@Serializable
data object Star : NavKey, BottomNavItem {
    override val icon: ImageVector
        get() = Icons.Filled.Star
    override val title: String
        get() = "Star"
}

@Serializable
data object StarDetail : NavKey

@Serializable
data object AccountBox : NavKey, BottomNavItem {
    override val icon: ImageVector
        get() = Icons.Filled.AccountBox
    override val title: String
        get() = "AccountBox"
}

interface BottomNavItem {
    val icon: ImageVector
    val title: String
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val bottomNavItems = listOf(Home, Star, AccountBox)
            val topLevelBackStack = remember { TopLevelBackStack<NavKey>(Home) }

            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            bottomNavItems.forEach { item ->
                                val selected = topLevelBackStack.topLevelKey == item
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = { topLevelBackStack.switchTopLevel(item) },
                                    icon = {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = null
                                        )
                                    },
                                    label = {
                                        Text(text = item.title)
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    val screenModifier = Modifier
                        .fillMaxSize()
                        .padding(
                            innerPadding
                        )
                    NavDisplay(
                        backStack = topLevelBackStack.backStack,
                        onBack = { topLevelBackStack.removeLast() },
                        entryProvider = entryProvider {
                            entry<Home> {
                                ContentGreen(
                                    modifier = screenModifier,
                                    title = "Welcome to Nav3"
                                ) {
                                    Button(onClick = {
                                        topLevelBackStack.add(Star)
                                    }) {
                                        Text("Click to navigate")
                                    }
                                }
                            }

                            entry<Star> {
                                ContentBlue(
                                    modifier = screenModifier,
                                    title = "Route id: $it "
                                ) {
                                    Button(onClick = {
                                        topLevelBackStack.add(StarDetail)
                                    }) {
                                        Text("Click to navigate")
                                    }
                                }
                            }

                            entry<StarDetail> {
                                ContentYellow(
                                    modifier = screenModifier,
                                    title = "Route id: $it "
                                )
                            }

                            entry<AccountBox> {
                                ContentMauve(
                                    modifier = screenModifier,
                                    title = "Route id: $it "
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

class TopLevelBackStack<T : NavKey>(private val startKey: T) {

    private var topLevelBackStacks: HashMap<T, SnapshotStateList<T>> = hashMapOf(
        startKey to mutableStateListOf(startKey)
    )

    var topLevelKey by mutableStateOf(startKey)
        private set

    val backStack = mutableStateListOf<T>(startKey)

    private fun updateBackStack() {
        backStack.clear()
        val currentStack = topLevelBackStacks[topLevelKey] ?: emptyList()

        if (topLevelKey == startKey) {
            backStack.addAll(currentStack)
        } else {
            val startStack = topLevelBackStacks[startKey] ?: emptyList()
            backStack.addAll(startStack + currentStack)
        }
    }

    fun switchTopLevel(key: T) {
        if (topLevelBackStacks[key] == null) {
            topLevelBackStacks[key] = mutableStateListOf(key)
        }
        topLevelKey = key
        updateBackStack()
    }

    fun add(key: T) {
        topLevelBackStacks[topLevelKey]?.add(key)
        updateBackStack()
    }

    fun removeLast() {
        val currentStack = topLevelBackStacks[topLevelKey] ?: return

        if (currentStack.size > 1) {
            currentStack.removeLastOrNull()
        } else if (topLevelKey != startKey) {
            topLevelKey = startKey
        }
        updateBackStack()
    }

    fun replaceStack(vararg keys: T) {
        topLevelBackStacks[topLevelKey] = mutableStateListOf(*keys)
        updateBackStack()
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        Greeting("Android")
    }
}