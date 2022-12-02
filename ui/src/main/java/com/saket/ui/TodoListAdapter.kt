package com.saket.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saket.domain.model.Todo

class TodoListAdapter (val deleteClickListener: (Todo) -> Unit) : ListAdapter<Todo, TodoListAdapter.TodoViewHolder>(TodoDiffCallback) {

    object TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }

    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(todo: Todo) {
            with(itemView) {
                val todoId = findViewById<TextView>(R.id.todoId)
                val todoDesc = findViewById<TextView>(R.id.todoDesc)
                val deleteIcon = findViewById<ImageView>(R.id.deleteTodo)
                deleteIcon.setOnClickListener {
                    deleteClickListener.invoke(todo)
                }
                todoId.text = todo.id.toString()
                todoDesc.text = todo.desc
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.todo_list_item, parent, false)
        return TodoViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currTodo = getItem(position)
        holder.bind(currTodo)
    }
}