package com.ecom.feature.admin_auth.ui

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
import com.ecom.feature.admin_auth.AdminLoginEffect
import com.ecom.feature.admin_auth.AdminLoginIntent
import com.ecom.feature.admin_auth.viewmodel.AdminLoginViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun AdminLoginScreen(
    viewModel: AdminLoginViewModel = koinViewModel(),
    onNavigateToAdminDashboard: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                is AdminLoginEffect.ShowError -> snackbarHostState.showSnackbar(it.message)
                AdminLoginEffect.NavigateToAdminDashboard -> onNavigateToAdminDashboard()
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
                value = state.email,
                onValueChange = { viewModel.onIntent(AdminLoginIntent.EmailChanged(it)) },
                label = { Text("Admin Email") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onIntent(AdminLoginIntent.PasswordChanged(it)) },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = { viewModel.onIntent(AdminLoginIntent.LoginClicked) },
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Login as Admin")
                }
            }
        }
    }
}
