package com.saket.domain.di

import com.saket.domain.model.ITodoRepository
import com.saket.domain.usecases.AddTodo
import com.saket.domain.usecases.GetAllTodos
import com.saket.domain.usecases.RemoveTodo
import dagger.Provides
import dagger.Module
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * TodoUseCaseModule in domain/di layer is responsible to provide instance of classes from
 * this layer (AddTodo, GetAllTodos, RemoveTodo etc.).
 *
 * Here, to provide instances of the usecase, there are 2 parameters: CoroutineDispatcher and
 * instance of ITodoRepository interface. Default CoroutineDispatcher instance is provided by
 * TodoUseCaseModule itself via @Provides annotated method.
 *
 * For ITodoRepository, this module relies on some other module (RoomModule in this case)
 * to provide the instance.
 *
 */
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