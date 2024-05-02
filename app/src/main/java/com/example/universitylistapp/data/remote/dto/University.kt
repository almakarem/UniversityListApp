package com.example.universitylistapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class University (
    @SerializedName("alpha_two_code")
    val alpha_two_code: String?,
    val country: String?,
    val domains: List<String>?,
    val name: String?,
    @SerializedName("state-province")
    val stateProvince: String?, // Change to match the JSON key
    @SerializedName("web_pages")
    val webPages: List<String>? // Change to match the JSON key
)