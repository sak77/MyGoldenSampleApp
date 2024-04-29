package com.saket.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class TodoViewModelFactory
@Inject
constructor(
    private val todoViewModel: TodoViewModel,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return todoViewModel as T
    }
}
