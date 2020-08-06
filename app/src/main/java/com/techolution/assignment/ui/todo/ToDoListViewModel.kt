package com.techolution.assignment.ui.todo

import androidx.lifecycle.*
import com.techolution.assignment.db.entities.TodoItem
import com.techolution.assignment.ui.todo.usecase.TodoUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ToDoListViewModel @Inject constructor(val todoUsecase: TodoUsecase) : ViewModel() {

    fun getTodoItemsList()= todoUsecase.getTodoListItems().asLiveData()
    suspend fun deleteTodoItem(todoItem: TodoItem) {
        todoUsecase.removeTodoItem(todoItem)
    }



    }
