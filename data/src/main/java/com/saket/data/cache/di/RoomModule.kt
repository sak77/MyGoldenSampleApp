package com.saket.data.cache.di

import android.content.Context
import com.saket.data.cache.TodoRepository
import com.saket.data.cache.TodoTable
import com.saket.data.cache.db.TodoDB
import com.saket.domain.model.ITodoRepository
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @Provides
    fun providesTodoDB(context: Context): TodoDB {
        return TodoDB.getInstance(context)
    }

    @Provides
    fun providesTodoTable(todoDB: TodoDB): TodoTable {
        return TodoTable(todoDB)
    }

    @Provides
    fun providesTodoRepository(todoTable: TodoTable): ITodoRepository {
        return TodoRepository(todoTable)
    }
}