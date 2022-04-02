package com.vxplore.agoraaudiocall.di

import android.content.Context
import com.hellomydoc.videocall.navigation.MyRouteNavigator
import com.hellomydoc.videocall.navigation.RouteNavigator
import com.vxplore.agoraaudiocall.*
import com.vxplore.agoraaudiocall.tokener.StaticTokenerImpl
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

    @Provides
    @Singleton
    fun provideMetar(@ApplicationContext application: Context): Metar {
        return MetarImpl(application)
    }

    @Provides
    fun provideTokenBuilder(): TokenBuilder {
        return TokenBuilderImpl()
    }

    @Provides
    fun provideTokener(credential: Credential, tokenBuilder: TokenBuilder): Tokener {
        return /*Static*/TokenerImpl(credential, tokenBuilder)
    }


}

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator(): RouteNavigator = MyRouteNavigator()

    @Provides
    @ViewModelScoped
    fun provideAgoraAudioCall(
        credential: Credential,
        tokener: Tokener,
        @ApplicationContext application: Context
    ): AgoraAudioCall {
        return AgoraAudioCallImpl(credential,tokener,application)
    }
}