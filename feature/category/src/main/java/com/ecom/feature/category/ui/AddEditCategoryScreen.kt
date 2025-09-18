package com.ecom.feature.category.ui

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
import com.ecom.feature.category.AddEditCategoryEffect
import com.ecom.feature.category.AddEditCategoryIntent
import com.ecom.feature.category.viewmodel.AddEditCategoryViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddEditCategoryScreen(
    viewModel: AddEditCategoryViewModel = koinViewModel(),
    onCategorySubmitted: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest {
            when (it) {
                is AddEditCategoryEffect.ShowError -> snackbarHostState.showSnackbar(it.message)
                AddEditCategoryEffect.CategorySubmitted -> onCategorySubmitted()
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
                value = state.name,
                onValueChange = { viewModel.onIntent(AddEditCategoryIntent.SetName(it)) },
                label = { Text("Category Name") }
            )
            OutlinedTextField(
                value = state.image,
                onValueChange = { viewModel.onIntent(AddEditCategoryIntent.SetImage(it)) },
                label = { Text("Image URL") }
            )
            Button(
                onClick = { viewModel.onIntent(AddEditCategoryIntent.SubmitCategory) },
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Submit Category")
                }
            }
        }
    }
}
