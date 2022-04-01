package com.vxplore.agoraaudiocall.di

import android.app.Application
import com.vxplore.agoraaudiocall.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCredential(): Credential {
        return CredentialMockImpl()
    }

    @Singleton
    @Provides
    fun provideMetar(@ApplicationContext application: Application): Metar {
        return MetarImpl(application)
    }

    @Provides
    fun provideTokenBuilder(): TokenBuilder {
        return TokenBuilderImpl()
    }
}