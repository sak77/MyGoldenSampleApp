package com.saket.ui.di

import android.content.Context
import com.saket.data.cache.di.RoomModule
import com.saket.domain.di.TodoUseCaseModule
import dagger.Module
import dagger.Provides

/**
 * TodoModule is composed of RoomModule from data layer
 * and TodoUseCaseModule from domain layer.
 *
 * TodoUseCaseModule requires instance of ITodoRepository
 * which is provided from the RoomModule via TodoModule class.
 */

@Module(includes = [RoomModule::class, TodoUseCaseModule::class])
class TodoModule constructor(private val context: Context) {
    @Provides
    fun providesContext(): Context {
        return context
    }
}
