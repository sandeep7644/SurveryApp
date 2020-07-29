package com.example.surveryapp.features.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ApplicationComponent::class)
class SharedPreferenceModule {

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) = context.getSharedPreferences("SurveySharedPreferences",MODE_PRIVATE)
}