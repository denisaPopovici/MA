package com.example.macruduinative.drugs.di

import com.example.macruduinative.details.usecase.*
import com.example.macruduinative.drugs.repo.DrugsRepository
import com.example.macruduinative.drugs.repo.DrugsRepositoryImpl
import com.example.macruduinative.drugs.usecase.GetDrugsUseCase
import com.example.macruduinative.drugs.usecase.GetDrugsUseCaseImpl
import com.example.macruduinative.newdrug.usecase.NewDrugUseCase
import com.example.macruduinative.newdrug.usecase.NewDrugUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt {

        @Binds
        @Singleton
        fun provideDrugsRepository(repo: DrugsRepositoryImpl): DrugsRepository

        @Binds
        @Singleton
        fun provideGetDrugsUseCase(uc: GetDrugsUseCaseImpl): GetDrugsUseCase

        @Binds
        @Singleton
        fun provideGetDrugUseCase(uc: GetDrugUseCaseImpl): GetDrugUseCase

        @Binds
        @Singleton
        fun provideNewDrugUseCase(uc: NewDrugUseCaseImpl): NewDrugUseCase

        @Binds
        @Singleton
        fun provideRemoveDrugUseCase(uc: RemoveDrugUseCaseImpl): RemoveDrugUseCase

        @Binds
        @Singleton
        fun provideUpdateDrugUseCase(uc: UpdateDrugUseCaseImpl): UpdateDrugUseCase

    }

}