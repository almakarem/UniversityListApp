package com.example.universitylistapp.domain.repository

import com.example.universitylistapp.data.remote.dto.University
import retrofit2.Response
import retrofit2.http.Query

interface UniversityRepository {

    suspend fun getAllUniversities(): Response<List<University>>

    suspend fun getUniversitiesByCountry(@Query("country") country: String): Response<List<University>>

    suspend fun getUniversitiesByName(@Query("name") name: String): Response<List<University>>

    suspend fun getUniversitiesByNameAndCountry(@Query("name") name: String,@Query("country") country: String): Response<List<University>>
}