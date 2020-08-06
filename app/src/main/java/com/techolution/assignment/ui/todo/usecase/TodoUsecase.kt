package com.techolution.assignment.ui.todo.usecase

import com.techolution.assignment.db.entities.TodoItem
import com.techolution.assignment.repository.TodoItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TodoUsecase @Inject constructor(val todoItemsRepository: TodoItemsRepository) {
    fun getTodoListItems(): Flow<List<TodoItem>> {
        return todoItemsRepository.getAllTodoItems()
    }
    suspend fun getTodoListItem(id:Int): TodoItem {
        return todoItemsRepository.getTodoListItem(id)
    }

    suspend fun updateTodoItem(todoItem: TodoItem) {
        todoItemsRepository.updateTodoItem(todoItem)
    }
    suspend fun insertTodoItem(todoItem: TodoItem) {
        todoItemsRepository.saveTodoItem(todoItem)
    }

    suspend fun removeTodoItem(todoItem: TodoItem) {
        todoItemsRepository.removeTodoItem(todoItem)
    }

}
