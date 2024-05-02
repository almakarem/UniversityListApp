package com.example.universitylistapp.data.mappers

import android.util.Log
import com.example.universitylistapp.data.local.UniversityEntity
import com.example.universitylistapp.data.remote.dto.University
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun University.toUniversityEntity(): UniversityEntity {
    Log.w("MAPPER", "toUniversityEntity: $", )
    return UniversityEntity(
        alpha_two_code = alpha_two_code ?: "N/A", // Providing a default value if null
        country = country ?: "Unknown Country",
        domains = Gson().toJson(domains),
        name = name ?: "Unknown Name",
        stateProvince = stateProvince ?: "Not Applicable",
        webPages = Gson().toJson(webPages)
    )
}

fun UniversityEntity.toUniversity(): University {
    return University(
        alpha_two_code = alpha_two_code ?: "N/A", // Providing a default value if null
        country = country ?: "Unknown Country",
        domains = Gson().fromJson(domains, object : TypeToken<List<String>>() {}.type),
        name = name ?: "Unknown Name",
        stateProvince = stateProvince ?: "Not Applicable",
        webPages = Gson().fromJson(webPages, object : TypeToken<List<String>>() {}.type)
    )
}

fun jsonToList(json: String): List<String> {

    // Create a TypeToken to represent the type List<String>
    val listType = object : TypeToken<List<String>>() {}.type

    // Convert the JSON string to a List<String> using Gson
    return Gson().fromJson(json, listType)?: listOf<String>()
}

