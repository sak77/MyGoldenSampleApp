package com.saket.domain.usecases

import com.saket.domain.model.ITodoRepository
import com.saket.domain.model.Todo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class GetAllTodos
constructor(
    private val todoRepository: ITodoRepository,
    private val executors: CoroutineDispatcher,
) {
    private val useCaseName = "GetAllTodos"

    /*
    Maybe instead of passing block to execute in collect method, one must consider
    returning Flow instance to calling class? Then we can call collect in the calling code?

    But block approach is also an option which is showcased here...
     */
    fun executeFlow(
        scope: CoroutineScope,
        block: (List<Todo>) -> Unit,
    ) {
        scope.launch {
            // todoRepository.getTodoList() is a flow returned from the db call.
            var job =
                todoRepository
                    .getTodoList()
                    .flowOn(executors)
                    .catch { ex -> }
                    .onCompletion { println("$useCaseName has completed") }
                    .collect { block(it) }
        }
    }
}
