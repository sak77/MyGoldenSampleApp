package com.saket.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.saket.ui.databinding.FragmentShowtodosBinding
import com.saket.ui.di.DaggerUIComponent
import com.saket.ui.di.TodoModule
import javax.inject.Inject

class TodoListFragment : Fragment() {

    @Inject
    lateinit var todoViewModelFactory: TodoViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerUIComponent.builder()
            .todoModule(TodoModule(context))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShowtodosBinding.inflate(inflater,container, false)
        binding.todoList.layoutManager = LinearLayoutManager(context)
        val todoViewModel: TodoViewModel by viewModels { todoViewModelFactory }
        //Here i also pass deleteclickListener function to the adapter instance.
        val todoListAdapter = TodoListAdapter {
            todoViewModel.removeTodo(it)
        }
        binding.todoList.adapter = todoListAdapter
        todoViewModel.todos.observe(viewLifecycleOwner) {
            todoListAdapter.submitList(it)
        }
        return binding.root
    }
}