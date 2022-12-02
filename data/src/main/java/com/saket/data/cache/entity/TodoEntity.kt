package com.saket.data.cache.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.saket.data.cache.dao.TodoDao
import com.saket.domain.model.Todo

@Entity(tableName = TodoDao.TODO_ENTRIES)
open class TodoEntity constructor(@Embedded val todo: Todo) {
    @PrimaryKey(autoGenerate = true)
    var pkId: Int = 0
}
