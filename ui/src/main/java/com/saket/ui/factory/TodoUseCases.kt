package com.saket.ui.factory

import android.content.Context
import com.saket.domain.usecases.AddTodo
import com.saket.domain.usecases.GetAllTodos
import com.saket.domain.usecases.RemoveTodo
import kotlinx.coroutines.Dispatchers

class TodoUseCases(context: Context) : TodoUseCaseFactory {
    private val todoRepository = RepositoryFactory.createTodoRepository(context)
    private val executors = Dispatchers.Default

    override fun createAddTodo(): AddTodo {
        return AddTodo(todoRepository, executors)
    }

    override fun createGetAllTodos(): GetAllTodos {
        return GetAllTodos(todoRepository, executors)
    }

    override fun createRemoveTodo(): RemoveTodo {
        return RemoveTodo(todoRepository, executors)
    }
}
