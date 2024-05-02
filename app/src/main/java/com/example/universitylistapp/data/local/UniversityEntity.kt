package com.example.universitylistapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UniversityEntity(
    @PrimaryKey
    val name: String, // Consider what should happen if this is null, since it's a primary key
    val alpha_two_code: String?,
    val country: String?,
    val domains: String,
    @SerializedName("state-province")
    val stateProvince: String?,
    @SerializedName("web_pages")
    val webPages: String
)