package com.techolution.assignment.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.techolution.assignment.db.RoomDB
import com.techolution.assignment.db.dao.TodoDao
import com.techolution.assignment.repository.TodoItemsRepository
import com.techolution.assignment.repository.TodoItemsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule {


    @Singleton
    @Provides
    fun providesRoomDatabase(application: Context): RoomDB {
        return Room.databaseBuilder(application, RoomDB::class.java, RoomDB.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesTodoDao(roomDB: RoomDB): TodoDao {
        return roomDB.todoDao()
    }

    @Singleton
    @Provides
    fun provideTodoItemsRepository(roomDB: RoomDB,todoDao: TodoDao):TodoItemsRepository{
        return TodoItemsRepositoryImpl(roomDB,todoDao)
    }

}
