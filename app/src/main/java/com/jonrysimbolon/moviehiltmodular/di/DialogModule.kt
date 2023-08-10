package com.jonrysimbolon.moviehiltmodular.di

import android.content.Context
import com.jonrysimbolon.core.dialog.CustomDialog
import com.jonrysimbolon.core.dialog.CustomDialogReload
import com.jonrysimbolon.core.dialog.ui.Failure
import com.jonrysimbolon.core.dialog.ui.Loading
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
object DialogModule{

    @ActivityScoped
    @Provides
    fun provideLoadingDialog(
        @ApplicationContext context: Context
    ): CustomDialog = Loading(context)

    @ActivityScoped
    @Provides
    fun provideFailureDialog(
        @ApplicationContext context: Context
    ): CustomDialogReload = Failure(context)
}