package com.todoapp.anciledigital.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todoapp.anciledigital.database.entity.Category
import com.todoapp.anciledigital.database.entity.TodoItem
import com.todoapp.anciledigital.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

    val categories = repository.getCategories()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _selectedCategoryId = MutableStateFlow<Int?>(null)
    val items = _selectedCategoryId.flatMapLatest { id ->
        id?.let { repository.getItemsByCategory(it) } ?: flowOf(emptyList())
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun selectCategory(id: Int) {
        _selectedCategoryId.value = id
    }

    fun addCategory(name: String) {
        viewModelScope.launch {
            repository.addCategory(Category(name = name))
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            repository.deleteCategory(category)
        }
    }

    fun addItem(name: String, categoryId: Int) {
        viewModelScope.launch {
            repository.addItem(TodoItem(categoryId = categoryId, name = name))
        }
    }

    fun deleteItem(item: TodoItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun toggleItemDone(item: TodoItem) {
        viewModelScope.launch {
            repository.updateItem(item.copy(isDone = !item.isDone))
        }
    }
}
