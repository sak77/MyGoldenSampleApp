package com.saket.ui.factory

import android.content.Context
import com.saket.data.cache.TodoRepository
import com.saket.data.cache.TodoTable
import com.saket.data.cache.db.TodoDB
import com.saket.domain.model.ITodoRepository

/** Internal modifier means this class cannot be accessed outside the module. */
internal object RepositoryFactory {
    fun createTodoRepository(context: Context): ITodoRepository {
        val todoDb = TodoDB.getInstance(context)
        val todoTable = TodoTable(todoDb)
        return TodoRepository(todoTable)
    }
}
