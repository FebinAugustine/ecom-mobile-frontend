package com.ecom.feature.seller_auth.ui

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
import com.ecom.feature.seller_auth.SellerLoginEffect
import com.ecom.feature.seller_auth.SellerLoginIntent
import com.ecom.feature.seller_auth.viewmodel.SellerLoginViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SellerLoginScreen(
    viewModel: SellerLoginViewModel = koinViewModel(),
    onNavigateToSellerDashboard: () -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                is SellerLoginEffect.ShowError -> snackbarHostState.showSnackbar(it.message)
                SellerLoginEffect.NavigateToSellerDashboard -> onNavigateToSellerDashboard()
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
                onValueChange = { viewModel.onIntent(SellerLoginIntent.EmailChanged(it)) },
                label = { Text("Seller Email") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onIntent(SellerLoginIntent.PasswordChanged(it)) },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = { viewModel.onIntent(SellerLoginIntent.LoginClicked) },
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Login as Seller")
                }
            }
        }
    }
}
