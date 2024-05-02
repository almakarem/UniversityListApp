package com.example.universitylistapp.data.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.universitylistapp.data.local.UniversityDatabase
import com.example.universitylistapp.data.local.UniversityEntity
import com.example.universitylistapp.data.mappers.toUniversityEntity
import com.example.universitylistapp.data.remote.dto.University
import kotlinx.coroutines.delay
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UniversityRemoteMediator(
    private val universityDb: UniversityDatabase,
    private val universityAPI: UniversityAPI
): RemoteMediator<Int,UniversityEntity>() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UniversityEntity>
    ): MediatorResult {
        return try{
            delay(2000L)
            val universities = universityAPI.getAllUniversities()

            universityDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    universityDb.dao.clearAll()
                }

                val universityEntity = universities.map {it.toUniversityEntity() }
                universityDb.dao.upsertAll(universityEntity)
            }

            MediatorResult.Success(
                endOfPaginationReached = universities.isEmpty()
            )
        }
        catch (e: IOException){
            MediatorResult.Error(e)
        }
        catch (e: HttpException){
            MediatorResult.Error(e)
        }
    }
}