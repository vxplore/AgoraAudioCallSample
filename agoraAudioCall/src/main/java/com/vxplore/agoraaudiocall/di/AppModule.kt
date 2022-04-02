package com.vxplore.agoraaudiocall.di

import android.app.Application
import com.hellomydoc.videocall.navigation.MyRouteNavigator
import com.hellomydoc.videocall.navigation.RouteNavigator
import com.vxplore.agoraaudiocall.*
import com.vxplore.agoraaudiocall.tokener.Tokener
import com.vxplore.agoraaudiocall.tokener.TokenerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
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

    @Provides
    fun provideTokener(credential: Credential, tokenBuilder: TokenBuilder): Tokener {
        return TokenerImpl(credential, tokenBuilder)
    }

    /*@Provides
    @Singleton
    fun provideAgoraAudioCall(
        credential: Credential,
        tokener: Tokener,
        @ApplicationContext application: Application
    ): AgoraAudioCall {
        return AgoraAudioCallImpl(credential,tokener,application)
    }*/
}

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator(): RouteNavigator = MyRouteNavigator()
}