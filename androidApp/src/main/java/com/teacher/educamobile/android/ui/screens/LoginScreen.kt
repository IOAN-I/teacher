package com.teacher.educamobile.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.teacher.educamobile.android.CoverImage
import com.teacher.educamobile.android.LoginButton
import com.teacher.educamobile.android.LoginTextInput
import com.teacher.educamobile.android.R

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
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