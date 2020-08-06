package com.techolution.assignment.di

import androidx.lifecycle.ViewModel
import com.techolution.assignment.ui.details.TodoItemDetailViewModel
import com.techolution.assignment.ui.todo.ToDoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ToDoListViewModel::class)
    abstract fun bindToDoListViewModel(toDoListViewModel: ToDoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TodoItemDetailViewModel::class)
    abstract fun bindTodoItemDetailsListViewModel(toDoListViewModel: TodoItemDetailViewModel): ViewModel
}
