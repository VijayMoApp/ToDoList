package com.todoapp.anciledigital.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.todoapp.anciledigital.database.entity.Category
import com.todoapp.anciledigital.database.entity.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {


    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM todo_items WHERE categoryId = :categoryId")
    fun getItemsByCategory(categoryId: Int): Flow<List<TodoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(todoItem: TodoItem)

    @Delete
    suspend fun deleteItem(todoItem: TodoItem)

    @Update
    suspend fun updateItem(todoItem: TodoItem)

}