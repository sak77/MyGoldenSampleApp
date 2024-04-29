package com.saket.data.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.saket.data.cache.dao.TodoDao
import com.saket.data.cache.entity.TodoEntity

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class TodoDB : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var todoDB: TodoDB? = null

        fun getInstance(context: Context): TodoDB =
            todoDB ?: synchronized(this) { todoDB ?: buildDatabase(context).also { todoDB = it } }

        private fun buildDatabase(context: Context): TodoDB {
            return Room.databaseBuilder(context, TodoDB::class.java, "Todo.db")
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }
                    },
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
