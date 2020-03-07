package com.appiness.cogni.tasktodo.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appiness.cogni.tasktodo.data.OperationCallback
import com.appiness.cogni.tasktodo.model.ApiResponse
import com.appiness.cogni.tasktodo.model.DataSource
import com.appiness.cogni.tasktodo.model.RowResponse
import kotlinx.android.synthetic.main.activity_main.*

class RowViewModel(private val repository: DataSource) : ViewModel() {
    private val _rows = MutableLiveData<List<RowResponse>>().apply { value = emptyList() }
    val rows_response: LiveData<List<RowResponse>> = _rows
    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading
    private val _onMessageError = MutableLiveData<Any>()
    private val _isEmptyList = MutableLiveData<Boolean>()

    companion object {
        var titleString: String = ""
    }

    fun refresh() {

        loadResponse()
    }

    fun loadResponse() {
        _isViewLoading.postValue(true)
        repository.retrieveResponse(object : OperationCallback<RowResponse> {
            override fun onSuccess(data: List<RowResponse>?, title: String?) {
                _isViewLoading.postValue(false)

                if (data != null) {
                    if (data.isEmpty()) {
                        _isEmptyList.postValue(true)
                    } else {
                        _rows.value = data
                        if (title != null) {
                            titleString = title
                        }

                    }
                }
            }

            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue(error)
            }
        })
    }
}