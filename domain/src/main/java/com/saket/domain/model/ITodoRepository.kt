package com.saket.domain.model

import kotlinx.coroutines.flow.Flow

/**
 * Interface is defined by the Domain layer. It can be
 * implemented by other data source in Data layer to
 * communicate with Domain layer..
 */
interface ITodoRepository {

    fun getTodoList(): Flow<List<Todo>>

    //Maybe return a Long Id here...
    fun addTodo(todo: Todo)

    fun removeTodo(todo: Todo): Int
}