package com.todoapp.anciledigital.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.todoapp.anciledigital.viewmodel.TodoViewModel

@Composable
fun CategoryList(viewModel: TodoViewModel, navController: NavController) {
    val categories by viewModel.categories.collectAsState()

    LazyColumn {
        items(categories) { category ->
            Text(category.name, Modifier.clickable {
                viewModel.selectCategory(category.id)
                navController.navigate("items/${category.id}")
            })
        }
    }


}