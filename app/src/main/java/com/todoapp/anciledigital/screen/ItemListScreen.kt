package com.todoapp.anciledigital.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.todoapp.anciledigital.viewmodel.TodoViewModel

@Composable
fun ItemList(viewModel: TodoViewModel) {
    val items by viewModel.items.collectAsState()

    LazyColumn {
        items(items) { item ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = item.isDone,
                    onCheckedChange = { viewModel.toggleItemDone(item) }
                )
                Text(item.name)
            }
        }
    }
}