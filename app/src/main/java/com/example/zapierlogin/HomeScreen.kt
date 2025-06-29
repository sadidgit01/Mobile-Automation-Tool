package com.example.zapierlogin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(userEmail: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🎉 Welcome!", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Text("You're logged in as:", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(userEmail, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
    }
}
