package com.techolution.assignment.db.dao

import androidx.room.*
import com.techolution.assignment.db.entities.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    suspend fun insertTodoItem(todoItem: TodoItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodoItem(todoItem: TodoItem)

    @Query("SELECT * FROM todo_table WHERE id = (:id)")
    suspend fun getTodoItem(id: Int): TodoItem

    @Delete
    suspend fun deleteTodoItem(todoItem: TodoItem)

    @Query("SELECT * FROM todo_table")
    fun getAllTodoItems(): Flow<List<TodoItem>>

}