package com.saket.ui.di

import com.saket.domain.model.ITodoRepository
import com.saket.ui.CreateTodoFragment
import com.saket.ui.TodoListFragment
import com.saket.ui.TodoViewModel
import dagger.Component
import javax.inject.Scope

/*
Here instead of Singleton i use a custom scope, this scope is tied to lifecycle of
the UiComponent (TodoListFragment/CreateTodoFragment).
 */
@Scope @Retention(value = AnnotationRetention.RUNTIME) annotation class FragmentScope

@FragmentScope
@Component(modules = [TodoModule::class])
interface UIComponent {
    fun todoRepository(): ITodoRepository

    fun todoViewModel(): TodoViewModel
    // fun dataSubComponent(): DataSubComponent.Factory

    // Field injections support for Fragments
    fun inject(todoListFragment: TodoListFragment)

    fun inject(createTodoFragment: CreateTodoFragment)
}
