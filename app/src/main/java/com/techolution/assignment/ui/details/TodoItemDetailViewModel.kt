package com.techolution.assignment.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.techolution.assignment.db.entities.TodoItem
import com.techolution.assignment.ui.todo.usecase.TodoUsecase
import javax.inject.Inject

class TodoItemDetailViewModel @Inject constructor(val todoUsecase: TodoUsecase) : ViewModel() {
    fun handleTodoInsert(title: String, descritpion: String, isChecked:Boolean)= liveData<Boolean> {
        emit(if (title.isNullOrEmpty() || descritpion.isNullOrEmpty()) {
            false
        } else {
            var todoItem = TodoItem(title, descritpion,isChecked)
            todoUsecase.insertTodoItem(todoItem)
            true
        })
    }
    fun handleTodoUpdate(
        id: Int,
        title: String,
        descritpion: String,
        isChecked: Boolean
    )= liveData<Boolean> {
        emit(if (title.isNullOrEmpty() || descritpion.isNullOrEmpty()) {
            false
        } else {
            var todoItem = TodoItem(title, descritpion,isChecked).apply { this.id = id}
            todoUsecase.updateTodoItem(todoItem)
            true
        })
    }

    fun getItem(itemId: Int) =  liveData {
        emit(todoUsecase.getTodoListItem(itemId))
    }
}