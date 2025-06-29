package com.example.zapierlogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = viewModel(),
    onGoogleSignInClick: () -> Unit
) {
    val isSignedIn = remember { mutableStateOf(viewModel.isUserSignedIn()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isSignedIn.value) {
            Button(onClick = onGoogleSignInClick) {
                Text("Sign in with Google")
            }
        } else {
            Text("Hello, ${viewModel.getUserName() ?: "User"}!")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.signOut()
                isSignedIn.value = false
            }) {
                Text("Sign Out")
            }
        }
    }
}
