package com.saket.domain.di

import com.saket.domain.model.ITodoRepository
import com.saket.domain.usecases.AddTodo
import com.saket.domain.usecases.GetAllTodos
import com.saket.domain.usecases.RemoveTodo
import dagger.Provides
import dagger.Module
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class TodoUseCaseModule {

    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    fun providesAddTodo(todoRepository: ITodoRepository, coroutineDispatcher: CoroutineDispatcher): AddTodo {
        return AddTodo(todoRepository, coroutineDispatcher)
    }

    @Provides
    fun providesGetAllTodos(todoRepository: ITodoRepository, coroutineDispatcher: CoroutineDispatcher): GetAllTodos {
        return GetAllTodos(todoRepository, coroutineDispatcher)
    }

    @Provides
    fun providesRemoveTodo(todoRepository: ITodoRepository, coroutineDispatcher: CoroutineDispatcher): RemoveTodo {
        return RemoveTodo(todoRepository, coroutineDispatcher)
    }
}