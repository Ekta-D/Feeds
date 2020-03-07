package com.appiness.cogni.tasktodo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appiness.cogni.tasktodo.model.DataSource

class ViewModelFactory(private val repository: DataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RowViewModel(repository) as T
    }
}