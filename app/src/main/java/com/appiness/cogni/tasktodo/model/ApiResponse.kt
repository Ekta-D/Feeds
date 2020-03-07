package com.appiness.cogni.tasktodo.model

import java.io.Serializable

data class ApiResponse(var title:String?,var rows:List<RowResponse>?): Serializable {
}