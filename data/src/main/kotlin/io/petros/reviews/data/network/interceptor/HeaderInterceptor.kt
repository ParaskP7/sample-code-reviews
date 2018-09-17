package io.petros.reviews.data.network.interceptor

import android.content.Context
import io.petros.reviews.data.R
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(val context: Context) : Interceptor {

    companion object {

        private const val HEADER_USER_AGENT = "User-Agent"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader(HEADER_USER_AGENT, context.getString(R.string.interceptor_getyourguide_user_agent))
            .build()
        return chain.proceed(newRequest)
    }

}
