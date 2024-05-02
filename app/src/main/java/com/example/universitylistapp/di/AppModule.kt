package com.example.universitylistapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.universitylistapp.data.local.UniversityDatabase
import com.example.universitylistapp.data.local.UniversityEntity
import com.example.universitylistapp.data.remote.UniversityAPI
import com.example.universitylistapp.data.remote.UniversityRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUniversityDatabase(@ApplicationContext context: Context): UniversityDatabase{
        return Room.databaseBuilder(
            context,
            UniversityDatabase::class.java,
            "univirsities.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUniversityAPI(): UniversityAPI{
        return Retrofit.Builder()
            .baseUrl(UniversityAPI.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideUniversityPager(universityDatabase: UniversityDatabase, universityAPI: UniversityAPI): Pager<Int, UniversityEntity>{
        return Pager(
            config = PagingConfig(pageSize = 50),
            remoteMediator = UniversityRemoteMediator(
                universityDatabase,
                universityAPI
            ),
            pagingSourceFactory = {
                universityDatabase.dao.paginSource()
            }
        )
    }

}