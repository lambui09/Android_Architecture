package com.lambui09.mvvm.data.common.retrofit

import android.annotation.SuppressLint
import android.content.Context
import com.lambui09.mvvm.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class ServiceGenerator private constructor(
    context: Context,
    baseUrl: String,
    headerInterceptor: Interceptor?,
    errorHandingInterceptor: Interceptor?
) {
    val retrofit: Retrofit

    companion object {
        const val CONNECT_TIME_OUT_DURATION = 60L
        const val READ_TIME_OUT_DURATION = 60L
        const val CACHE_MAX_SIZE = (10 * 10 * 1000).toLong()
    }

    private val logging = HttpLoggingInterceptor()
        .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

    init {
        val cache = Cache(context.cacheDir, CACHE_MAX_SIZE)

        val client = getUnsafeOkHttpClient(
            context,
            logging,
            cache,
            headerInterceptor,
            errorHandingInterceptor
        )
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    @SuppressLint("TrustAllX509TrustManager")
    private fun getUnsafeOkHttpClient(
        context: Context, logger: HttpLoggingInterceptor, cache: Cache,
        headerInterceptor: Interceptor?,
        errorHandingInterceptor: Interceptor?
    ): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier(HostnameVerifier { _, _ -> true })
                .addInterceptor(logger)
                .addInterceptor(ConnectivityInterceptor(context))
                .connectTimeout(CONNECT_TIME_OUT_DURATION, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT_DURATION, TimeUnit.SECONDS)
                .cache(cache)
            errorHandingInterceptor?.let { builder.addInterceptor(it) }
            if (headerInterceptor != null && !builder.interceptors().contains(headerInterceptor)) {
                builder.addNetworkInterceptor(headerInterceptor)
            }
            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    class Builder(private val context: Context, private val baseUrl: String) {

        private var headerInterceptor: Interceptor? = null

        private var errorHandingInterceptor: Interceptor? = null

        fun headerInterceptor(headerInterceptor: Interceptor): Builder {
            this.headerInterceptor = headerInterceptor
            return this
        }

        fun errorHandingInterceptor(errorHandingInterceptor: Interceptor): Builder {
            this.errorHandingInterceptor = errorHandingInterceptor
            return this
        }

        fun build(): ServiceGenerator {
            return ServiceGenerator(context, baseUrl, headerInterceptor, errorHandingInterceptor)
        }

    }

}