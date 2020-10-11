package com.shekhar.uobtest.di

import com.shekhar.uobtest.model.ServerService
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {

    @Provides
    fun provideServerService(): ServerService {
        return ServerService()
    }
}