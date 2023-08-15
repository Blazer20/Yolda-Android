package com.yolda.presentation.di

import android.app.Activity
import android.app.Application
import android.content.Context
import com.yolda.common.SnackBarManager
import com.yolda.domain.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.firestore.FirebaseFirestore
import com.yolda.domain.PhoneNumberAuthUiClient
import com.yolda.presentation.ui.activity.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Application Injection
    @Provides
    @Singleton
    fun provideApplicationContext(
        application: Application
    ): Context = application.applicationContext

    // View Injection
    @Provides
    @Singleton
    fun provideSnackBarManager(): SnackBarManager = SnackBarManager

    // Firebase FireStore Injection
    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideSignInClient(
        @ApplicationContext context: Context
    ): SignInClient = Identity.getSignInClient(context)

    @Provides
    @Singleton
    fun provideGoogleAuthUiClient(
        @ApplicationContext context: Context,
        signInClient: SignInClient
    ): GoogleAuthUiClient = GoogleAuthUiClient(context, signInClient)

    @Provides
    @Singleton
    fun providePhoneNumberAuthUiClient(
        activity: Activity
    ): PhoneNumberAuthUiClient = PhoneNumberAuthUiClient(activity)
}
