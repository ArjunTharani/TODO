package com.techolution.assignment.repository

import com.techolution.assignment.db.entities.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoItemsRepository {
    fun getAllTodoItems(): Flow<List<TodoItem>>
    suspend fun getTodoListItem(id:Int): TodoItem
    suspend fun saveTodoItem(todoItem: TodoItem)
    suspend fun updateTodoItem(todoItem: TodoItem)
    suspend fun removeTodoItem(todoItem: TodoItem)
}