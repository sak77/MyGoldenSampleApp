package com.saket.domain.usecases

import com.saket.domain.model.ITodoRepository
import com.saket.domain.model.Todo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Some characteristics of Use-cases - Single responsibility - Each use-case should provide a single
 * function that performs a single task. Business logic - Usecase should only perform business logic
 * not any other task like UI logic (for example, formatting displayed text on the screen).
 * Framework dependencies - ideally use-cases should not consider framework apis. This allows
 * isolation of the domain layer. Which helps to make domain layer re-usable. If there is need for
 * domain layer to have dependencies to Android framework apis then these can be provided via an
 * interface from the data layer. But this will then increase bolierplate code, which is again not
 * desirable. So it really depends how strictly one wants to follow clean-architecture.
 * Over-abstraction - Use Generic parameters in use-cases only if the code is being re-used.
 */
class AddTodo(
    private val todoRepository: ITodoRepository,
    private val executors: CoroutineDispatcher,
) {
    private val useCaseName = "GetAllTodos"

    /*
    CoroutineScope.launch is a fire-and-forget kind of coroutine builder function.
    Unlike async-await. It does not return any value to the calling function.
    But what if one has to execute some code after the coroutine has completed its work?

    Earlier, sending lambda function as a callback parameter to the function was one way to
    invoke code from the calling function after coroutine has finished its task execution.
    But sending a lambda to the coroutine function, can also cause memory leaks. For eg.
    if the calling function passes its Context to the coroutine method. In this case, if the
    calling component is to be garbage collected while the coroutine performs a long running task,
    then the gc will not be able to remove instance of the class from heap memory. This will cause
    a memory leak.

    Also, each lambda passed as a parameter, is converted into an object in decompiled java code.

    Another option can be to use Job instance returned by the launch method.
    The launch method returns a Job instance which is a handle to the coroutine. It
    provides methods to cancel, or wait (join) for job to complete and also it
    provides a method called invokeOnCompletion which can be used to execute code when
    the job completes.

    So, here i have returned the job instance from executeOnBackground. This
    instance is used in calling viewmodel to execute code after the coroutine job is
    complete.
     */
    fun executeOnBackground(
        scope: CoroutineScope,
        args: Todo,
    ): Job = scope.launch(executors) { todoRepository.addTodo(args) }
}
