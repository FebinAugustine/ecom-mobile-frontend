package com.ecom.feature.seller_auth.ui

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
import com.ecom.feature.seller_auth.SellerSignupEffect
import com.ecom.feature.seller_auth.SellerSignupIntent
import com.ecom.feature.seller_auth.viewmodel.SellerSignupViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SellerSignupScreen(
    viewModel: SellerSignupViewModel = koinViewModel(),
    onNavigateToSellerDashboard: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                is SellerSignupEffect.ShowError -> snackbarHostState.showSnackbar(it.message)
                SellerSignupEffect.NavigateToSellerDashboard -> onNavigateToSellerDashboard()
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
                onValueChange = { viewModel.onIntent(SellerSignupIntent.FullnameChanged(it)) },
                label = { Text("Full Name") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onIntent(SellerSignupIntent.EmailChanged(it)) },
                label = { Text("Email") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.phone,
                onValueChange = { viewModel.onIntent(SellerSignupIntent.PhoneChanged(it)) },
                label = { Text("Phone") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onIntent(SellerSignupIntent.PasswordChanged(it)) },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextField(
                value = state.address,
                onValueChange = { viewModel.onIntent(SellerSignupIntent.AddressChanged(it)) },
                label = { Text("Address") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.companyName,
                onValueChange = { viewModel.onIntent(SellerSignupIntent.CompanyNameChanged(it)) },
                label = { Text("Company Name") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.location,
                onValueChange = { viewModel.onIntent(SellerSignupIntent.LocationChanged(it)) },
                label = { Text("Location") },
                singleLine = true
            )
            Button(
                onClick = { viewModel.onIntent(SellerSignupIntent.SignupClicked) },
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Sign Up as Seller")
                }
            }
        }
    }
}
