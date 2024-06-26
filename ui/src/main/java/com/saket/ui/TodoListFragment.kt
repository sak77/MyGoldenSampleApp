package com.saket.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TodoListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_showtodos, container, false)
        val todoList = rootView.findViewById<RecyclerView>(R.id.todo_list)
        todoList.layoutManager = LinearLayoutManager(context)
        val todoViewModel: TodoViewModel by viewModels {
            TodoViewModel.Companion.Factory(context?.applicationContext as Application)
        }
        // Here i also pass deleteclickListener function to the adapter instance.
        val todoListAdapter = TodoListAdapter { todoViewModel.removeTodo(it) }
        todoList.adapter = todoListAdapter
        todoViewModel.todos.observe(viewLifecycleOwner) { todoListAdapter.submitList(it) }
        return rootView
    }
}
