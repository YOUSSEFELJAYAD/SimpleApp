package com.simple.di.SubModules


import com.simple.utils.DeviceInfoHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppProviders {
    @Singleton
    @Provides
    fun getDeviceServiceInst(): DeviceInfoHelper {
        return DeviceInfoHelper()
    }
}