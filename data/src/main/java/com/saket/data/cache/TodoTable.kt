package com.saket.data.cache

import com.saket.data.cache.db.TodoDB
import com.saket.data.cache.entity.TodoEntity
import com.saket.domain.model.Todo
import kotlinx.coroutines.flow.Flow

/**
 * This class performs db operations on TodoTable.
 */
class TodoTable constructor(private val db: TodoDB) {

    fun addNewTodo(todo: TodoEntity) = db.todoDao().addTodo(todo)

    fun removeTodo(todoEntity: TodoEntity) = db.todoDao().removeTodo(todoEntity.todo.id)

    fun getAllTodos(): Flow<List<TodoEntity>> = db.todoDao().getTodo()
}
