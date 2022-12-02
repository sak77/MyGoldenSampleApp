package com.saket.data.cache.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.saket.data.cache.entity.TodoEntity
import com.saket.domain.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    companion object {
        const val TODO_ENTRIES = "todo_entries"
    }

    @Query("SELECT * FROM $TODO_ENTRIES")
    fun getTodo(): Flow<List<TodoEntity>>

    @Insert
    fun addTodo(todo: TodoEntity)

    /*
    delete returns number of rows deleted...
    @Delete is not working. Maybe since i am not passing
    the auto-generated id in Todo class.
     */
    @Query("DELETE FROM todo_entries WHERE id = :todoId")
    fun removeTodo(todoId: Int): Int
}
