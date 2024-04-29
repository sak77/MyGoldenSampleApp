package com.saket.domain.usecases

import com.saket.domain.model.ITodoRepository
import com.saket.domain.model.Todo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RemoveTodo
constructor(
    private val todoRepository: ITodoRepository,
    private val executors: CoroutineDispatcher,
) {
    fun executeOnBackground(
        coroutineScope: CoroutineScope,
        todo: Todo,
    ): Job =
        coroutineScope.launch(executors) {
            val rowsDeleted = todoRepository.removeTodo(todo)
            println("Bunny rows deleted $rowsDeleted")
        }
}
