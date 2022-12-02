package com.saket.domain.usecases

import com.saket.domain.model.ITodoRepository
import com.saket.domain.model.Todo
import kotlinx.coroutines.*

class AddTodo constructor(
    private val todoRepository: ITodoRepository,
    private val executors: CoroutineDispatcher
) {
    private val useCaseName = "GetAllTodos"

    /*
    CoroutineScope.launch is a fire-and-forget kind of coroutine builder function.
    Unlike async-await. It does not return any value to the calling function.
    But what if one has to execute some code after the coroutine has completed its work?

    Earlier, it was thought sending lambda function as a callback parameter to the
    function was the only way to invoke code from the calling function after coroutine
    has finished its task execution. But it is found that sending a lambda to the coroutine
    function, can also cause memory leaks. For eg. if the calling function passes its Context
    to the coroutine method. In this case, if the calling component is to be garbage collected
    while the coroutine performs a long running task, then the gc will not be able to remove
    instance of the class from heap memory. This will cause a memory leak.

    Also, each lambda passed as a parameter, is converted into an object in decompiled java code.

    Another option can be to use Job instance returned by the launch method.
    The launch method returns a Job instance which is a handle to the coroutine. It
    provides methods to cancel, or wait (join) for job to complete and also it
    provides a method called invokeOnCompletion which can be used to execute code when
    the job completes.

    So, in this case, i have returned the job instance from executeOnBackground. This
    instance is used in calling viewmodel to execute code after the coroutine job is
    complete.
     */
    fun executeOnBackground(scope: CoroutineScope, args: Todo): Job = scope.launch(executors) {
        todoRepository.addTodo(args)
    }
}
