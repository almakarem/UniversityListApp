package com.example.universitylistapp.data.remote.dto

data class University(
    val alphaTwoCode: String,
    val country: String,
    val domains: List<String>,
    val name: String,
    val stateProvince: String,
    val webPages: List<String>
)