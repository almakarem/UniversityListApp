package com.example.universitylistapp.data.remote

import com.example.universitylistapp.data.remote.dto.University
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityAPI {

    @GET("search") //http://universities.hipolabs.com/search
    suspend fun getAllUniversities(): List<University>

    @GET("search") //http://universities.hipolabs.com/search?country=United%20Arab%20Emirates
    suspend fun getUniversitiesByCountry(
        @Query("country") country: String
    ): List<University>

    @GET("search")
    suspend fun getUniversitiesByName(
        @Query("name") name: String
    ): List<University>

    @GET("search")
    suspend fun getUniversitiesByNameAndCountry(
        @Query("name") name: String,
        @Query("country") country: String
    ): List<University>

    companion object {
        const val BASE_URL = "http://universities.hipolabs.com/"
    }
}