package com.saket.data.cache.di

import android.content.Context
import com.saket.data.cache.TodoRepository
import com.saket.data.cache.TodoTable
import com.saket.data.cache.db.TodoDB
import com.saket.domain.model.ITodoRepository
import dagger.Module
import dagger.Provides

/**
 * RoomModule is responsible to provide instance of classes from
 * this layer (TodoDB, TodoTable and TodoRepository).
 */
@Module
class RoomModule {

    /*
    TodoDB instance has dependency on instance of Context class. Which is provided as a parameter
    to the providesTodoDB method. Which means, a component or some other module in the Dagger graph
    will provide the context instance. In other words this module has some kind of dependency
    which needs to be satisfied by the Component/Module using it.
     */
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