package com.example.vijaywfhassignment.di

import com.example.vijaywfhassignment.data.remote.api.WatchModeApi
import com.example.vijaywfhassignment.util.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideWatchModeApi(retrofit: Retrofit): WatchModeApi =
        retrofit.create(WatchModeApi::class.java)
}
