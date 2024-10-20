package com.simple.di.Networks

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.simple.utils.DeviceInfoHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkService {
    private val TAG = "Network-Service"

    @Singleton
    @Provides
    fun ProvideOkHttpInst(_Interceptor: Interceptor?): OkHttpClient {
        val client = OkHttpClient.Builder()
        if (_Interceptor != null)
            client.addInterceptor(_Interceptor)
        client.connectTimeout(60, TimeUnit.SECONDS)
        client.writeTimeout(60, TimeUnit.SECONDS)
        client.readTimeout(60, TimeUnit.SECONDS)

        return client.build()
    }

    @Singleton
    @Provides
    fun provideRESTInterceptor(
        mAuth: FirebaseUser?,
        CachControleInst: CacheControl ,
        _DeviceInfoHelper: DeviceInfoHelper
    ): Interceptor? {

        return Interceptor { chain ->
            val original = chain.request()
            var request = original.newBuilder()
                //.header("Accept-Encoding", "gzip, deflate, br")
                .header("User-Agent", "PostmanRuntime/7.29.2")
                .header("Accept", "application/json")
                .header("Connection", "keep-alive")
                .cacheControl(CachControleInst)
                .method(original.method, original.body)
                .build()
            /**
             *  Remove TOcken Injection For Firebase Injection
             *
             * */
            if (!original.url.toString().contains("/LOGIN", true) && mAuth != null
            ) {
                val Token = getToken(mAuth)
                request = request.newBuilder()
                    .header("Authorization", "Bearer " + Token)
                    .header("ShaMobile", _DeviceInfoHelper.Generate_Signature())
                    .cacheControl(CachControleInst)
                    .method(original.method, original.body)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            Log.e(TAG, "provideRESTInterceptor: " + original.url.toString())
            chain.proceed(request)
        }
    }

    @Provides
    fun CacheControlImpl(): CacheControl {
        return CacheControl.Builder()
            .maxAge(5 , TimeUnit.MINUTES) // 5 minutes cache
            .build()
    }

    private fun getToken(mAuth: FirebaseUser): String? {
        val token = StringBuilder()
        val countDownLatch = CountDownLatch(1)
        mAuth.getIdToken(false).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                token.append(task.result.token)
            }
            countDownLatch.countDown()
        }
        println(token)
        return try {
            countDownLatch.await(15L, TimeUnit.SECONDS)
            token.toString()
        } catch (ie: InterruptedException) {
            null
        }
    }

}
class NullOnEmptyConverterFactory @Inject constructor() : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ) = Converter<ResponseBody, Any?> {
        if (it.contentLength() != 0L) retrofit.nextResponseBodyConverter<Any?>(
            this,
            type,
            annotations
        ).convert(it) else null
    }
}