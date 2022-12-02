package com.saket.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

abstract class UseCase<TInput, TOutput> () {

    private var parentJob: Job = Job()

    protected abstract val usecaseName: String

    protected abstract suspend fun executeOnBackground(args: TInput): TOutput

    /*
    fun execute(scope: CoroutineScope, args: TInput, block: CompletionBlock<TOutput> ) {
    }
     */
}
