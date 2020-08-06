package com.techolution.assignment.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoItem (
    var title: String,
    var description: String,
    var isChecked: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}