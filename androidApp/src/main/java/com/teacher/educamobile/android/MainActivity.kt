package com.teacher.educamobile.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.teacher.educamobile.android.ui.theme.TeacherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeacherTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    App()
//                }
                Scaffold { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
//                        App(Modifier.padding(innerPadding))
                        Column(
                            modifier = Modifier.padding(horizontal = 12.dp)
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            HomeScreen()
                            HomeScreen()
                            HomeScreen()
                            HomeScreen()
                            HomeScreen()
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel()
) {
    TeacherTheme(

    ) {
        CompositionLocalProvider(
            androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current
        ) {
//            val greetings by mainViewModel.greetingList.collectAsStateWithLifecycle()
//
//            Column(
//                modifier = Modifier.padding(all = 20.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp),
//            ) {
//                greetings.forEach { greeting ->
//                    Text(greeting)
//                    HorizontalDivider()
//                }
//            }
            val transition = rememberInfiniteTransition(label = "")
            val animatedProgress by transition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1500, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                ), label = ""
            )
            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
            val density = LocalDensity.current

            val brush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.inversePrimary,
//                    MaterialTheme.colorScheme.surfaceTint
                ),
                startY = 0f,
//                endY = with(density) { screenHeight.toPx() * animatedProgress })
                endY = with(density) { screenHeight.toPx()})

            val reusableModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
            Column(
                modifier = modifier
                    .background(color = MaterialTheme.colorScheme.secondaryContainer),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoverImage(Modifier.weight(0.7f))
                Column(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
//                    verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                    LoginTextInput(
                        modifier = reusableModifier,
                        label = "Usuario",
                        leadingIcon = Icons.Default.AccountCircle
                    )
                    LoginTextInput(
                        modifier = reusableModifier,
                        label = "Contraseña",
                        leadingIcon = Icons.Default.Lock,
                        trailingIcon = ImageVector.vectorResource(R.drawable.eye)
                    )
                    LoginButton(reusableModifier.padding(vertical = 10.dp))
                }
            }
        }

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
fun HomeScreen() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Entrevista",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "03 jun 2024",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text(
                        text = "Sayumi Mayerly Zola Huanca",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Prueba de reunión",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "10:25 am",
                            modifier = Modifier,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 3.dp)
                                .size(18.dp),
                            painter = painterResource(
                                id = R.drawable.comment_eye_outline,
                            ),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "")
                        Text(
                            text = "Sin observación",
                            fontSize = 12.sp
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 3.dp)
                                .size(18.dp),
                            painter = painterResource(
                                id = R.drawable.calendar_check_outline
                            ),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Realizado"
                        )
                        Text(
                            text = "Sin observación",
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    TeacherTheme {
//        App()
        HomeScreen()
    }
}
