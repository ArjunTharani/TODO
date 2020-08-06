package com.techolution.assignment.di

import androidx.appcompat.app.AppCompatActivity
import com.techolution.assignment.MainActivity
import com.techolution.assignment.ui.details.TodoItemDetailsFragment
import com.techolution.assignment.ui.todo.ToDoListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @Binds
    @ActivityScope
    abstract fun appCompatActivity(mainActivity: MainActivity): AppCompatActivity


    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun todoListFragmentInjector(): ToDoListFragment

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun todoItemDetailsFragmentInjector(): TodoItemDetailsFragment

}
