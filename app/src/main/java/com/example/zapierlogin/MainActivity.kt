package com.example.zapierlogin
import androidx.lifecycle.ViewModelProvider
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.example.zapierlogin.SignInViewModelFactory


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zapierlogin.ui.theme.ZapierLoginTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        


        // ✅ Google Sign-In Options
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            ZapierLoginTheme {
                Surface {
                    val currentUser = FirebaseAuth.getInstance().currentUser


                    println("👤 Firebase currentUser: $currentUser")

                    if (currentUser != null) {
                        HomeScreen(userEmail = currentUser.email ?: "Unknown User")
                    } else {
                        SignInScreen(
                            onGoogleSignInClick = {
                                signInLauncher.launch(googleSignInClient.signInIntent)
                            }
                        )
                    }
                }
            }
        }


    }

    // ✅ Activity Result Launcher
    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            if (idToken != null) {
                // Show toast that token is received
                Toast.makeText(this, "✅ Token received!", Toast.LENGTH_SHORT).show()
                println("✅ idToken: $idToken")

                // ✅ Use custom factory to get ViewModel
                val factory = SignInViewModelFactory(application)
                val viewModel = ViewModelProvider(this, factory)[SignInViewModel::class.java]

                viewModel.firebaseAuthWithGoogle(idToken) { success ->
                    if (success) {
                        Toast.makeText(this, "✅ Login Successful!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "❌ Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "❌ Token is null", Toast.LENGTH_SHORT).show()
                println("❌ idToken was null!")
            }

        } catch (e: ApiException) {
            e.printStackTrace()
        }
    }

}



// This is just to show