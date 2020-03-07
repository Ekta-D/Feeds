package com.appiness.cogni.tasktodo.di

import androidx.lifecycle.ViewModelProvider
import com.appiness.cogni.tasktodo.model.DataSource
import com.appiness.cogni.tasktodo.model.Repositry
import com.appiness.cogni.tasktodo.viewmodel.ViewModelFactory

object Injection {

    private val rowDataSource: DataSource = Repositry()
    private val rowViewModelFactory = ViewModelFactory(rowDataSource)



    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return rowViewModelFactory
    }
}