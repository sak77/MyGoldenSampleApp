package com.saket.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saket.domain.model.Todo
import com.saket.domain.usecases.AddTodo
import com.saket.domain.usecases.GetAllTodos
import com.saket.domain.usecases.RemoveTodo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoViewModel(
    getAllTodos: GetAllTodos,
    private val addTodo: AddTodo,
    private val removeTodo: RemoveTodo,
) : ViewModel() {
    private val _todos: MutableLiveData<List<Todo>> = MutableLiveData(listOf())
    val todos: LiveData<List<Todo>> = _todos

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Default("default"))
    val uiState: StateFlow<UiState> = _uiState

    init {
        getAllTodos.executeFlow(viewModelScope) { list -> _todos.value = list }
    }

    fun addTodo(todo: Todo) {
        _uiState.value = UiState.Loading("Loading")
        addTodo.executeOnBackground(viewModelScope, todo).invokeOnCompletion {
            if (it == null) {
                println("addTodo finished without errors")
                _uiState.value = UiState.Completed("Completed")
            } else {
                println(
                    "addTodo throwable is not null. Maybe its canclled or " +
                        "maybe its an error.. $it",
                )
                _uiState.value = UiState.Error(it.message.toString())
            }
        }
    }

    fun removeTodo(todo: Todo) {
        _uiState.value = UiState.Loading("Loading")
        removeTodo.executeOnBackground(viewModelScope, todo).invokeOnCompletion {
            if (it == null) {
                println("removeTodo finished without errors")
                _uiState.value = UiState.Completed("Completed")
            } else {
                println(
                    "removeTodo throwable is not null. Maybe its canclled or " +
                        "maybe its an error.. $it",
                )
                _uiState.value = UiState.Error(it.message.toString())
            }
        }
    }

    /*
    Sealed class is similar to Enums. But while in Enum, each instance
    is a Constant, the Sealed class instance is a child of the parent class.
     */
    sealed class UiState {
        data class Default(val message: String) : UiState()

        data class Loading(val message: String) : UiState()

        data class Completed(val message: String) : UiState()

        data class Error(val message: String) : UiState()
    }
}
