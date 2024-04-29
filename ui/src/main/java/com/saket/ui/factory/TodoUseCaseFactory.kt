package com.saket.ui.factory

import com.saket.domain.usecases.AddTodo
import com.saket.domain.usecases.GetAllTodos
import com.saket.domain.usecases.RemoveTodo

interface TodoUseCaseFactory {
    fun createAddTodo(): AddTodo

    fun createGetAllTodos(): GetAllTodos

    fun createRemoveTodo(): RemoveTodo
}
