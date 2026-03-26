package com.example.wewatch4.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wewatch4.presentation.viewmodel.MovieIntent
import com.example.wewatch4.presentation.viewmodel.MovieViewModel

@Composable
fun SearchScreen(viewModel: MovieViewModel, onBack: () -> Unit) {
    val state by viewModel.state.collectAsState()
    var query by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search movie...") },
            trailingIcon = {
                IconButton(onClick = { viewModel.handleIntent(MovieIntent.Search(query)) }) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        LazyColumn {
            items(state.searchResults) { movie ->
                ListItem(
                    headlineContent = { Text(movie.title) },
                    supportingContent = { Text(movie.year) },
                    modifier = Modifier.clickable {
                        viewModel.handleIntent(MovieIntent.Add(movie))
                        onBack()
                    }
                )
            }
        }
    }
}