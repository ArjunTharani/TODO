package com.techolution.assignment.di

import android.content.Context
import com.techolution.assignment.TodoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<TodoApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): AppComponent
    }
}