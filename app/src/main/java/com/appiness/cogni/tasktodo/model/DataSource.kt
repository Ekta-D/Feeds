package com.appiness.cogni.tasktodo.model

import com.appiness.cogni.tasktodo.data.OperationCallback

interface DataSource {


    fun retrieveResponse(callback: OperationCallback<RowResponse>)
    fun cancel()
}