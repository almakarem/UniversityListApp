package com.example.universitylistapp.data.mappers

import com.example.universitylistapp.data.local.UniversityEntity
import com.example.universitylistapp.data.remote.dto.University
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun University.toUniversityEntity(): UniversityEntity {
    return UniversityEntity(
        alphaTwoCode = alphaTwoCode,
        country = country,
        domains = Gson().toJson(domains),
        name = name,
        stateProvince = stateProvince,
        webPages = Gson().toJson(webPages)
    )
}

fun UniversityEntity.toUniversity(): University {
    return University(
        alphaTwoCode = alphaTwoCode,
        country = country,
        domains =  jsonToList(domains),
        name = name,
        stateProvince = stateProvince,
        webPages = jsonToList(webPages)
    )
}

fun jsonToList(json: String): List<String> {
    // Create a TypeToken to represent the type List<String>
    val listType = object : TypeToken<List<String>>() {}.type

    // Convert the JSON string to a List<String> using Gson
    return Gson().fromJson(json, listType)
}

