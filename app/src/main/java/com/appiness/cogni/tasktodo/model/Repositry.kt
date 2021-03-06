package com.appiness.cogni.tasktodo.model

import android.util.Log
import com.appiness.cogni.tasktodo.data.ApiClient
import com.appiness.cogni.tasktodo.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repositry : DataSource {

    private var call: Call<ApiResponse>? = null

    companion object{
        var isBadResponse: Boolean=false
    }
    override fun retrieveResponse(callback: OperationCallback<RowResponse>) {

        call = ApiClient.build()?.fetchResponse()
        call?.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.i("Error is",t.message)
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                response?.body()?.let {
                    Log.i("data", response.toString())
                    if (response.isSuccessful) {
                        callback.onSuccess(response.body()!!.rows, response.body()!!.title)
                    } else {
                        callback.onError(it.toString())
                    }

                    if(response.code()==404)
                    {
                        isBadResponse=true
                        callback.onError("Bad Url Request")
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}