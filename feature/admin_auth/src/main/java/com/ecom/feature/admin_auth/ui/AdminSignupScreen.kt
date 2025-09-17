package com.ecom.feature.admin_auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.ecom.feature.admin_auth.AdminSignupEffect
import com.ecom.feature.admin_auth.AdminSignupIntent
import com.ecom.feature.admin_auth.viewmodel.AdminSignupViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun AdminSignupScreen(
    viewModel: AdminSignupViewModel = koinViewModel(),
    onNavigateToAdminDashboard: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                is AdminSignupEffect.ShowError -> snackbarHostState.showSnackbar(it.message)
                AdminSignupEffect.NavigateToAdminDashboard -> onNavigateToAdminDashboard()
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.fullname,
                onValueChange = { viewModel.onIntent(AdminSignupIntent.FullnameChanged(it)) },
                label = { Text("Full Name") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onIntent(AdminSignupIntent.EmailChanged(it)) },
                label = { Text("Email") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.phone,
                onValueChange = { viewModel.onIntent(AdminSignupIntent.PhoneChanged(it)) },
                label = { Text("Phone") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onIntent(AdminSignupIntent.PasswordChanged(it)) },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = { viewModel.onIntent(AdminSignupIntent.SignupClicked) },
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Sign Up as Admin")
                }
            }
        }
    }
}
