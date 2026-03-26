package com.example.wewatch4.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wewatch4.domain.model.Movie
import com.example.wewatch4.presentation.viewmodel.MovieIntent
import com.example.wewatch4.presentation.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MovieViewModel, onNavigateToSearch: () -> Unit) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Watchlist") },
                actions = {
                    IconButton(onClick = { viewModel.handleIntent(MovieIntent.DeleteSelected) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete checked")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToSearch) {
                Icon(Icons.Default.Add, contentDescription = "Search")
            }
        }
    ) { padding ->
        if (state.watchlist.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Your watchlist is empty")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(state.watchlist) { movie ->
                    MovieItem(
                        movie = movie,
                        onCheckedChange = { isChecked ->
                            viewModel.handleIntent(MovieIntent.ToggleChecked(movie, isChecked))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onCheckedChange: (Boolean) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = movie.poster,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Text(text = movie.year, style = MaterialTheme.typography.bodySmall)
            }
            Checkbox(checked = movie.isChecked, onCheckedChange = onCheckedChange)
        }
    }
}