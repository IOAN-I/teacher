package com.teacher.educamobile.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.teacher.educamobile.android.ui.screens.LoginScreen
import com.teacher.educamobile.android.ui.screens.MeetingsScreen
import com.teacher.educamobile.android.ui.theme.TeacherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TeacherTheme {

                CompositionLocalProvider(
                    androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current
                ) {
                    // TODO 1: if user is logged in go to meetings screen.
                    if(false)
                        LoginScreen(navController = navController)
                    else
                        MainAppNavigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    CompositionLocalProvider(
        androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current
    ) {
        // TODO 1: if user is logged in go to meetings screen.
        if(true)
            LoginScreen(navController = navController)
        else
            MeetingsScreen(navController = navController)
    }
}

@Composable
fun LoginTextInput(
    modifier: Modifier = Modifier,
    label: String,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector = Icons.Default.Clear,
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        label = { Text(text = label) },
        onValueChange = { newText ->
            text = newText
        },
        textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = ""
            )
        },
        trailingIcon = {
            Icon(
                imageVector = trailingIcon,
                modifier = Modifier.size(25.dp),
                contentDescription = ""
            )
        }
    )
}

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = { /*TODO*/ },
//        colors = ButtonColors()
    ) {
        Text(text = "Ingresar")
    }
}

@Composable
fun CoverImage(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 50.dp),
    ) {
        Image(
            modifier = modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.ic_school_cover),
            contentDescription = "",
        )

    }
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "A" ) {
        composable("A") { MeetingsScreen(navController = navController) }
        composable("B") { App(navController = navController) }
        composable("C") { App(navController = navController) }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    items: List<NavItem>,
    currentRoute: String?,
) {
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun MainAppNavigation(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavigationItems = listOf(
        NavItem.Meetings,
        NavItem.Attendance,
        NavItem.Notifications
    )

    val bottomBarRoutes = bottomNavigationItems.map { i -> i.route }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Scaffold(
            bottomBar = {
                if (currentRoute in bottomBarRoutes) {
                    BottomBar(
                        navController = navController,
                        items = bottomNavigationItems,
                        currentRoute = currentRoute
                    )
                }
            }
        ) { innerPadding ->
            AppNavigation(modifier = Modifier.padding(innerPadding), navController = navController)
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    TeacherTheme {
//        App()
//        MeetingCard()
    }
}
