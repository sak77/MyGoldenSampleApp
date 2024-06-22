package com.saket.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.saket.domain.model.Todo
import com.saket.domain.usecases.AddTodo
import com.saket.domain.usecases.GetAllTodos
import com.saket.domain.usecases.RemoveTodo
import com.saket.ui.factory.TodoUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoViewModel(
    getAllTodos: GetAllTodos,
    private val addTodo: AddTodo,
    private val removeTodo: RemoveTodo,
) : ViewModel() {
    private val _todos: MutableLiveData<List<Todo>> = MutableLiveData(listOf())
    val todos: LiveData<List<Todo>> = _todos

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Default)
    val uiState: StateFlow<UiState> = _uiState

    var testUIState: TestUIState = TestUIState.LOADING

    init {
        getAllTodos.executeFlow(viewModelScope) { list -> _todos.value = list }
    }

    fun addTodo(todo: Todo) {
        _uiState.value = UiState.Loading
        addTodo.executeOnBackground(viewModelScope, todo).invokeOnCompletion {
            if (it == null) {
                println("addTodo finished without errors")
                _uiState.value = UiState.Completed
                testUIState = TestUIState.COMPLETED
            } else {
                println(
                    "addTodo throwable is not null. Maybe its canclled or " +
                        "maybe its an error.. $it",
                )
                _uiState.value = UiState.Error(it.message.toString())
                testUIState = TestUIState.ERROR
            }
        }
    }

    fun removeTodo(todo: Todo) {
        _uiState.value = UiState.Loading
        removeTodo.executeOnBackground(viewModelScope, todo).invokeOnCompletion {
            if (it == null) {
                println("removeTodo finished without errors")
                _uiState.value = UiState.Completed
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
    Sub-classes of Sealed class or interface are defined (sealed) at compile time.
    This allows compiler to run extensive checks when using Sealed class
    with 'when' condition.

    Sealed class vs Enums
    Sealed class is similar to Enums. But while in Enum, each instance
    is a Constant, the Sealed class instance is a sub-class of the parent class.
    This allows for each sub-class to have its own member variables on top
    of those required by the parent class.

    Member variables used by parent class are applicable to all sub-classes.
     */
    sealed class UiState(val message: String) {
        object Default : UiState("Default")

        object Loading : UiState("Loading")

        object Completed : UiState("Completed")

        data class Error(val description: String) : UiState("Error")
    }

    /*
    Enums is another way to define States, but then each instance is a Constant of parent type.
    It can also be used for UI states, but does not offer as much flexibility as Sealed class.
     */
    enum class TestUIState(val message: String) {
        LOADING("LOADING"),
        READY("READY"),
        COMPLETED("COMPLETED"),
        ERROR("ERROR"),
    }

    /*
    It is recommended to have viewmodel factory defined within viewmodel class companion object
     */
    companion object {
        class Factory(application: Application) :
            ViewModelProvider.AndroidViewModelFactory(application) {
            private val todoUseCases = TodoUseCases(application.applicationContext)

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
                    TodoViewModel(
                        todoUseCases.createGetAllTodos(),
                        todoUseCases.createAddTodo(),
                        todoUseCases.createRemoveTodo(),
                    )
                        as T
                } else {
                    super.create(modelClass)
                }
            }
        }
    }
}
