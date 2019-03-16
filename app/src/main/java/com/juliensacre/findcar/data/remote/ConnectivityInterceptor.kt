package com.juliensacre.findcar.data.remote

import okhttp3.Interceptor

/**
 * Intercept the interceptor of API request
 * It's use for the dependency injection
 */
interface ConnectivityInterceptor :Interceptor