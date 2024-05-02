package com.example.universitylistapp.data.remote

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.universitylistapp.data.local.UniversityDatabase
import com.example.universitylistapp.data.local.UniversityEntity
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class UniversityRemoteMediator(
    private val universityDb: UniversityDatabase,
    private val universityAPI: UniversityAPI
): RemoteMediator<Int, UniversityEntity>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UniversityEntity>
    ): MediatorResult {
        return try {
            delay(2000L)
            val universitiesResponse = universityAPI.getAllUniversities()

            

            universityDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    universityDb.dao.clearAll()
                }
                universityDb.dao.clearAll()

                val universities = universitiesResponse.map {
                    UniversityEntity(
                        alpha_two_code = it.alpha_two_code ?: "",
                        country = it.country ?: "",
                        domains = Gson().toJson(it.domains),
                        name = it.name ?: "",
                        stateProvince = it.stateProvince ?: "",
                        webPages = Gson().toJson(it.webPages)
                    )
                }

                universityDb.dao.upsertAll(universities)
            }

            MediatorResult.Success(
                endOfPaginationReached = universitiesResponse.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}