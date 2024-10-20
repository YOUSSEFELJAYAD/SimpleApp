package com.simple.di.Auth

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AuthModule {
    @Singleton
    @Provides
    fun getFirebaseUserInt(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    @Singleton
    @Provides
    fun getFirebaseInt():  FirebaseApp{
        return FirebaseApp.getInstance()
    }
}




@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserFirebaseInst
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UserFirebaseToken