package com.todoapp.anciledigital.repository

import com.todoapp.anciledigital.database.dao.TodoDao
import com.todoapp.anciledigital.database.entity.Category
import com.todoapp.anciledigital.database.entity.TodoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TodoRepository  @Inject constructor(private val todoDao: TodoDao) {
    fun getCategories(): Flow<List<Category>> = todoDao.getCategories()

    suspend fun addCategory(category: Category) {
        todoDao.insertCategory(category)
    }

    suspend fun deleteCategory(category: Category) {
        todoDao.deleteCategory(category)
    }

    fun getItemsByCategory(categoryId: Int): Flow<List<TodoItem>> =
        todoDao.getItemsByCategory(categoryId)

    suspend fun addItem(todoItem: TodoItem) {
        todoDao.insertItem(todoItem)
    }

    suspend fun deleteItem(todoItem: TodoItem) {
        todoDao.deleteItem(todoItem)
    }

    suspend fun updateItem(todoItem: TodoItem) {
        todoDao.updateItem(todoItem)
    }
}