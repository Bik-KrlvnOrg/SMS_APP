package com.cheise_proj.sms_app.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.cheise_proj.core.utils.NetworkState
import com.cheise_proj.sms_app.extension.networkInfo
import javax.inject.Inject

class NetworkStateImpl @Inject constructor(private val context: Context) : NetworkState {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun isConnected(): Boolean = context.networkInfo()
}