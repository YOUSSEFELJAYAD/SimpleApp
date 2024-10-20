package com.flex.hilt.Moduls


import android.content.Context
import android.content.SharedPreferences
import com.simple.di.ProviderComposable.GProvider

import com.simple.config.Const
import com.simple.config.network
import com.simple.di.storageService.StorageService
import com.simple.services.networkService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.simple.di.listeners.Listeners
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
class MainModules {

    @Singleton
    @Provides
    fun _retrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            //.addConverterFactory(NullOnEmptyConverterFactory())
            //.addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun _api(retrofit: Retrofit): network {
        return retrofit.create(network::class.java)
    }




    @Singleton
    @Provides
    fun _networkService(network: network ): networkService {
        return networkService(network)
    }

    @Singleton
    @Provides
    fun _GProvider(t:networkService, storageService :StorageService ,
                   _Listeners :Listeners) : GProvider {
        return GProvider(t , storageService, _Listeners );
    }


    @Singleton
    @Provides
    @Named("app-league-shared-pref")
    fun getSharedPrefleague(@ApplicationContext appContext: Context) = appContext.getSharedPreferences("app-league", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun _StorageService( @Named( "app-league-shared-pref" )  league: SharedPreferences,):
            StorageService {
        return StorageService(league)
    }


    @Singleton
    @Provides
    fun getListeners( @ApplicationContext appContext: Context ): Listeners {
        return Listeners(appContext)
    }
}