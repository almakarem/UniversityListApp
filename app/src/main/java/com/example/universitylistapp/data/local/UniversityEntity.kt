package com.example.universitylistapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UniversityEntity(
    @PrimaryKey
    val name: String,
    val alphaTwoCode: String,
    val country: String,
    val domains: String,
    val stateProvince: String,
    val webPages: String
)