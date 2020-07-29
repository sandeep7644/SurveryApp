package com.example.surveryapp.di

import android.content.Context
import androidx.room.Room
import com.example.surveryapp.persistance.SurveyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class PersistanceModule {


    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): SurveyDatabase {
        return Room.databaseBuilder(context, SurveyDatabase::class.java, "SurveyDatabase")
            .build()
    }

    @Provides
    fun provideUserDao(surveyDatabase: SurveyDatabase) = surveyDatabase.userDao()

    @Provides
    fun provideSurveyDao(surveyDatabase: SurveyDatabase) = surveyDatabase.surveyDao()


}