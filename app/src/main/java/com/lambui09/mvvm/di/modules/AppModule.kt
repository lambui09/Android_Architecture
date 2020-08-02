package com.lambui09.mvvm.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lambui09.mvvm.data.Constants.BASE_URL
import com.lambui09.mvvm.data.common.retrofit.ServiceGenerator
import com.lambui09.mvvm.data.remote.AppApi
import com.lambui09.mvvm.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @ApplicationContext
    fun provideApplicationContext(context: Context): Context {
        return context.applicationContext
    }

    @Provides
    fun providerServiceGenerator(@ApplicationContext context: Context): ServiceGenerator {
        return ServiceGenerator.Builder(context, BASE_URL)
            //.headerInterceptor(HeaderInterceptor()) // Add HeaderInterceptor if needed
            //.errorHandingInterceptor(ErrorInterceptor(gson))
            .build()
    }

    @Provides
    @Singleton
    fun providerAppApi(serviceGenerator: ServiceGenerator): AppApi {
        return serviceGenerator.createService(AppApi::class.java)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            //Custom options here
            .create()
    }

}