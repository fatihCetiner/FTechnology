package com.example.ftechnology.data.di

import com.example.ftechnology.data.repository.AuthRepositoryImpl
import com.example.ftechnology.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
@InstallIn(SingletonComponent::class)
@Module
object FireBaseModule {

    @[Provides Singleton]
    fun provideFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @[Provides Singleton]
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }
}

 */