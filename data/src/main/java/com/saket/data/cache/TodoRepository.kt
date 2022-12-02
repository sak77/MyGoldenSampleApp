package com.saket.data.cache

import com.saket.data.cache.entity.TodoEntity
import com.saket.domain.model.ITodoRepository
import com.saket.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * The repository class is responsible to pass data between
 * data and domain layers. It implements ITodoRepository interface
 * defined in the domain layer. It will use the todoTable to
 * make the actual requests. While the mapper will transform
 * the data from data object to domain object.
 */
class TodoRepository constructor(private val todoTable: TodoTable) : ITodoRepository {

    override fun getTodoList(): Flow<List<Todo>> = todoTable.getAllTodos().map { list ->
        list.map { todoEntity ->
            todoEntity.todo
        }
    }

    override fun addTodo(todo: Todo) = todoTable.addNewTodo(TodoEntity(todo))

    override fun removeTodo(todo: Todo) = todoTable.removeTodo(TodoEntity(todo))
}