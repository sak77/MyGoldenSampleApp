package com.saket.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.textfield.TextInputEditText
import com.saket.domain.model.Todo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CreateTodoFragment : Fragment() {
    private val todoViewModel: TodoViewModel by viewModels { TodoViewModelFactory(context?.applicationContext as Application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                todoViewModel.uiState.collect {
                    uiState -> when(uiState) {
                        is TodoViewModel.UiState.Default -> defaultState()
                        is TodoViewModel.UiState.Loading -> displayLoading()
                        is TodoViewModel.UiState.Completed -> closeFragment()
                        is TodoViewModel.UiState.Error -> displayError(uiState.message)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_newtodo, container, false)
        val submitTodo = rootView.findViewById<Button>(R.id.submit_todo)
        val todoText = rootView.findViewById<TextInputEditText>(R.id.todo_description)
        submitTodo.setOnClickListener { view ->
            val id = (0..5).random()
            todoViewModel.addTodo(Todo(id, todoText.text.toString()))
        }
        return rootView
    }

    fun defaultState() {
        println("Bunny CreateFragment is in Default State")
    }

    fun closeFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this@CreateTodoFragment)
            .commit()
    }

    fun displayLoading() {
        println("Bunny CreateFragment is in Loading State")
    }

    fun displayError(message: String) {
        println("Bunny CreateFragment is in Error State")
    }

}