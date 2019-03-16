package com.juliensacre.findcar.data.remote

import android.content.Context
import android.net.ConnectivityManager
import com.juliensacre.findcar.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Check if the phone have connectivity (Wifi, 3/4G) before all request
 */
class ConnectivityInterceptorImpl (context: Context): ConnectivityInterceptor{

    //use applicationContext because if activity or fragment are destroyed i don't lose the context
    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isOnline())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}