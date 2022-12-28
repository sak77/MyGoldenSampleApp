package com.saket.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saket.ui.factory.TodoUseCases

class TodoViewModelFactory (application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    private val todoUseCases = TodoUseCases(application.applicationContext)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            TodoViewModel(todoUseCases.createGetAllTodos(),
            todoUseCases.createAddTodo(),
            todoUseCases.createRemoveTodo()) as T
        } else {
            super.create(modelClass)
        }
    }
}