package com.ebayk.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {


    fun isNetworkConnected(context: Context): Boolean {

        val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mActiveNetwork = mConnectivityManager.activeNetworkInfo
        return mActiveNetwork != null && mActiveNetwork.isConnectedOrConnecting

    }
}