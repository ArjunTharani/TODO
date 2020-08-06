package com.techolution.assignment.repository

import com.techolution.assignment.db.RoomDB
import com.techolution.assignment.db.dao.TodoDao
import com.techolution.assignment.db.entities.TodoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoItemsRepositoryImpl @Inject constructor(val roomDB: RoomDB, val todoDao: TodoDao) :
    TodoItemsRepository {
    override fun getAllTodoItems(): Flow<List<TodoItem>> {
       return todoDao.getAllTodoItems()
    }
    override suspend fun getTodoListItem(id:Int):TodoItem{
       return todoDao.getTodoItem(id)
    }

    override suspend fun saveTodoItem(todoItem: TodoItem) {
       todoDao.insertTodoItem(todoItem)
    }
    override suspend fun updateTodoItem(todoItem: TodoItem) {
       todoDao.updateTodoItem(todoItem)
    }

    override suspend fun removeTodoItem(todoItem: TodoItem) {
        todoDao.deleteTodoItem(todoItem)
    }
}