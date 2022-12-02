package com.saket.data.cache.mapper

import com.saket.data.cache.entity.TodoEntity
import com.saket.domain.model.Todo

/**
 * Mappers are used to map data of one module to another type.
 * However, in this case it is not being used...
 */
class TodoMapper {

    fun mapFromEntity(todoEntity: TodoEntity): Todo {
        return todoEntity.todo    }
}