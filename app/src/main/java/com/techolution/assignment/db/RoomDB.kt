package com.techolution.assignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techolution.assignment.db.RoomDB.Companion.VERSION
import com.techolution.assignment.db.dao.TodoDao
import com.techolution.assignment.db.entities.TodoItem

@Database(entities = [TodoItem::class], version = VERSION, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        const val DATABASE_NAME = "todoListDb"
        const val VERSION = 3
    }

}