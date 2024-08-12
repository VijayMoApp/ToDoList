package com.todoapp.anciledigital.database

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.todoapp.anciledigital.database.dao.TodoDao
import com.todoapp.anciledigital.database.entity.Category
import com.todoapp.anciledigital.database.entity.TodoItem

@Database(entities = [Category::class, TodoItem::class], version = 1)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}