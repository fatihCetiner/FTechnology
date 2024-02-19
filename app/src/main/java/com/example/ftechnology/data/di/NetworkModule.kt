package com.example.ftechnology.data.di

import com.example.ftechnology.data.repository.ProductRepositoryImpl
import com.example.ftechnology.data.source.remote.ProductApi
import com.example.ftechnology.domain.repository.ProductRepository
import com.example.ftechnology.util.Constants
import com.example.ftechnology.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @[Provides Singleton]
    fun provideProductApi(): ProductApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }

    @[Provides Singleton]
    fun provideProductRepository(api: ProductApi): ProductRepository{
        return ProductRepositoryImpl(api = api)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}