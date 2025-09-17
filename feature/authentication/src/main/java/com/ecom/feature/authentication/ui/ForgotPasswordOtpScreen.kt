package com.ecom.feature.authentication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.ecom.feature.authentication.ForgotPasswordOtpEffect
import com.ecom.feature.authentication.ForgotPasswordOtpIntent
import com.ecom.feature.authentication.viewmodel.ForgotPasswordOtpViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordOtpScreen(
    viewModel: ForgotPasswordOtpViewModel = koinViewModel(),
    onNavigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                is ForgotPasswordOtpEffect.ShowError -> snackbarHostState.showSnackbar(it.message)
                ForgotPasswordOtpEffect.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.otp,
                onValueChange = { viewModel.onIntent(ForgotPasswordOtpIntent.OtpChanged(it)) },
                label = { Text("OTP") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.newPassword,
                onValueChange = { viewModel.onIntent(ForgotPasswordOtpIntent.NewPasswordChanged(it)) },
                label = { Text("New Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = { viewModel.onIntent(ForgotPasswordOtpIntent.SubmitClicked) },
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Submit")
                }
            }
        }
    }
}
