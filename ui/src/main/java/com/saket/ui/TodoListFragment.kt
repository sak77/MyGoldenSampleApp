package com.saket.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saket.ui.di.DaggerUIComponent
import com.saket.ui.di.TodoModule
import javax.inject.Inject

class TodoListFragment : Fragment() {

    @Inject
    lateinit var todoViewModel: TodoViewModel

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
        val rootView = inflater.inflate(R.layout.fragment_showtodos, container, false)
        val todoList = rootView.findViewById<RecyclerView>(R.id.todo_list)
        todoList.layoutManager = LinearLayoutManager(context)
        //Here i also pass deleteclickListener function to the adapter instance.
        val todoListAdapter = TodoListAdapter {
            todoViewModel.removeTodo(it)
        }
        todoList.adapter = todoListAdapter
        todoViewModel.todos.observe(viewLifecycleOwner) {
            todoListAdapter.submitList(it)
        }
        return rootView
    }
}