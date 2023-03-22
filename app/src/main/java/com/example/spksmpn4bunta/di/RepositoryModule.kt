package com.example.spksmpn4bunta.di

import com.example.spksmpn4bunta.datastore.DataStore
import com.example.spksmpn4bunta.repository.Repository
import com.example.spksmpn4bunta.service.ApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideRepository(apiHelper: ApiHelper,dataStore: DataStore) = Repository(apiHelper,dataStore)
}