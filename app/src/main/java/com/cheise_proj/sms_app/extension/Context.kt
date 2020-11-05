package com.cheise_proj.sms_app.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities


@Suppress("DEPRECATION")
fun Context.networkInfo(): Boolean {
    val connectivityManager =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

        val network: Network = connectivityManager.activeNetwork ?: return false

        val activeNetwork: NetworkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
