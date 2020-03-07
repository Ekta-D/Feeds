package com.appiness.cogni.tasktodo.data

import com.appiness.cogni.tasktodo.model.ApiResponse
import com.appiness.cogni.tasktodo.model.RowResponse

interface OperationCallback<T> {
    fun onSuccess(data: List<RowResponse>?,  title: String?)
    fun onError(error:String?)
}